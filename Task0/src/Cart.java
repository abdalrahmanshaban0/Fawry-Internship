import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a shopping cart that can hold products, track their quantity, subtotal
 * and collect shippable items.
 */
public class Cart {
    /**
     * The total price of all items in the cart.
     */
    private double subtotal = 0;
    /**
     * A map storing each product and its quantity in the cart.
     */
    private final Map<Product, Integer> items = new HashMap<>();
    /**
     * A list of all shippable items added to the cart.
     */
    private final List<Shippable> shippableItems = new ArrayList<>();

    /**
     * Adds a product to the cart with the specified quantity.
     *
     * @param product  the product to be added
     * @param quantity the quantity of the product to add
     * @throws IllegalArgumentException if the quantity is invalid or
     *                                  not enough stock is available, or the product is expired
     */
    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Invalid quantity.");

        if (product.getQuantity() < quantity + items.getOrDefault(product, 0)) {
            throw new IllegalArgumentException("No enough stock of " + product.getName());
        }

        if (product.isExpirable()) {
            ExpirableProduct expirableProduct = (ExpirableProduct) product;
            if (expirableProduct.isExpired()) {
                throw new IllegalArgumentException("Product (" + product.getName() + ") has expired.");
            }
        }

        subtotal += product.getPrice() * quantity;

        if (product.isShippable()) {
            Shippable shippableProduct = (Shippable) product;
            for (int i = 0; i < quantity; i++) {
                shippableItems.add(shippableProduct);
            }
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    /**
     * Returns items in the cart.
     *
     * @return a reference to map of products to quantities
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Get the list of shippable items in the cart. Quantities more than 1 is repeated in the list.
     *
     * @return a reference to list of shippable items in the cart.
     */
    public List<Shippable> getShippableItems() {
        return shippableItems;
    }

    /**
     * Returns the subtotal (total price) of the items in the cart.
     *
     * @return The subtotal value
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Clears the cart by resetting subtotal, shippable list and item list.
     */
    public void clear() {
        subtotal = 0;
        shippableItems.clear();
        items.clear();
    }
}
