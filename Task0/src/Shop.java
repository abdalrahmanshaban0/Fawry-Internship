import java.util.List;
import java.util.Map;

/**
 * Utility class that handles the checkout process for a {@link Customer} and his {@link Cart}.
 * <p>
 * This class cannot be instantiated and contains only a static method {@link #checkout(Cart, Customer)}.
 */
public class Shop {
    /**
     * Private constructor to prevent instantiation.
     */
    private Shop(){}

    /**
     * Processes the checkout for a given customer and cart.
     * <p>
     * This method performs the following operations:
     * <ul>
     *     <li>Validates the cart is not empty.</li>
     *     <li>Checks if the customer has enough balance for subtotal + shipping.</li>
     *     <li>Ships all shippable items via {@link ShippingService}.</li>
     *     <li>Prints a formatted receipt including items, subtotal, shipping, and total.</li>
     *     <li>Deducts purchased quantities from product stock.</li>
     *     <li>Deducts the total amount from the customer's balance.</li>
     *     <li>Clears the cart after successful checkout.</li>
     * </ul>
     *
     * @param cart     the shopping cart containing selected products
     * @param customer the customer making the purchase
     * @throws IllegalArgumentException if the cart is empty or if the customer's balance is insufficient
     */
    public static void checkout(Cart cart, Customer customer) {
        Map<Product, Integer> items = cart.getItems();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items in this cart.");
        }

        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();

        // customer must has at least 30 in his/her balance to check out
        if (subtotal + (shippableItems.isEmpty() ? 0 : ShippingService.shippingCost) > customer.getBalance()) {
            throw new IllegalArgumentException("Customer's balance is insufficient.");
        }

        // Process shipping
        double shippingFees = ShippingService.ship(shippableItems);
        System.out.println();

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
            System.out.printf("%sx %-20s %6.1fEGP\n", quantity ,product.getName(), product.getPrice() * quantity);
        }
        System.out.println("----------------------");
        System.out.printf("%-20s %6.1fEGP\n", "Subtotal", subtotal);
        System.out.printf("%-20s %6.1fEGP\n", "Shipping", shippingFees);
        System.out.printf("%-20s %6.1fEGP\n", "Amount", shippingFees + subtotal);

        // Deduct balance
        customer.setBalance(customer.getBalance() - subtotal - shippingFees);

        System.out.println("----------------------");
        System.out.printf("%-20s %6.1fEGP\n", "Customer Balance", customer.getBalance());

        // Clear cart after checkout
        cart.clear();
    }
}
