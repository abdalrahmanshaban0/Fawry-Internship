import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Test Case 1: Successful Checkout ===");
        testSuccessfulCheckout();

        System.out.println("\n=== Test Case 2: Insufficient Balance ===");
        testInsufficientBalance();

        System.out.println("\n=== Test Case 3: Expired Product ===");
        testExpiredProduct();

        System.out.println("\n=== Test Case 4: Exceeding Stock ===");
        testExceedingStock();

        System.out.println("\n=== Test Case 5: Empty Cart Checkout ===");
        testEmptyCartCheckout();

        System.out.println("\n=== Test Case 6: Duplicate Product Add ===");
        testDuplicateProductAdd();

        System.out.println("\n=== Test Case 7: Zero Weight Shipping ===");
        testZeroWeightShipping();

        System.out.println("\n=== Test Case 8: Exact Balance ===");
        testExactBalance();
    }


    /**
     * Test successful checkout with mixed expirable and shippable items.
     */
    public static void testSuccessfulCheckout() {
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 20, LocalDate.of(2026, 12, 12), 200);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 10, LocalDate.of(2026, 12, 12), 700);
        Product TV = new ShippableProduct("Samsung Oled 60 inch", 20000, 3, 25350);
        Product milk = new ExpirableProduct("Milk", 25, 200, LocalDate.of(2025, 12, 12));

        Customer customer = new Customer(25000);
        Cart cart = customer.cart;

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(TV, 1);
        cart.add(milk, 1);

        Shop.checkout(cart, customer);

        System.out.println("\nExpected: Successful checkout with receipt and customer balance: 0.0EGP");
    }

    /**
     * Test a checkout that should fail due to insufficient balance.
     */
    public static void testInsufficientBalance() {
        Product tv = new ShippableProduct("Sony TV", 15000, 1, 10.0);
        Customer customer = new Customer(10000);
        Cart cart = customer.cart;

        try {
            cart.add(tv, 1);
            Shop.checkout(cart, customer);
            System.out.println("FAILED: Expected an exception due to insufficient balance.");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: " + e.getMessage());
            System.out.println("Expected: Customer's balance is insufficient.");
        }
    }

    /**
     * Test adding an expired product to the cart.
     */
    public static void testExpiredProduct() {
        Product expiredMilk = new ExpirableShippableProduct("Milk", 60, 5, LocalDate.of(2020, 1, 1), 0.5);
        Customer customer = new Customer(500);
        Cart cart = customer.cart;

        try {
            cart.add(expiredMilk, 1);
            System.out.println("FAILED: Expected an exception due to expired product.");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: " + e.getMessage());
            System.out.println("Expected: Product (Milk) has expired.");
        }
    }

    /**
     * Test adding more than available stock.
     */
    public static void testExceedingStock() {
        Product chocolate = new ShippableProduct("Chocolate Box", 50, 3, 0.3);
        Customer customer = new Customer(1000);
        Cart cart = customer.cart;

        try {
            cart.add(chocolate, 4); // Exceeds stock
            System.out.println("FAILED: Expected an exception due to insufficient stock.");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: " + e.getMessage());
            System.out.println("Expected: No enough stock of Chocolate Box");
        }
    }

    /**
     * Test trying to check out with an empty cart.
     */
    public static void testEmptyCartCheckout() {
        Customer customer = new Customer(1000);
        Cart cart = customer.cart;

        try {
            Shop.checkout(cart, customer);
            System.out.println("FAILED: Expected an exception due to empty cart.");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed: " + e.getMessage());
            System.out.println("Expected: No items in this cart.");
        }
    }

    public static void testDuplicateProductAdd() {
        Product charger = new ShippableProduct("Phone Charger", 100, 10, 200);
        Customer customer = new Customer(1000);
        Cart cart = customer.cart;

        cart.add(charger, 2);
        cart.add(charger, 3); // Total 5

        Shop.checkout(cart, customer);
        System.out.println("Expected: 5x Phone Charger, subtotal = 500, shipping = 30, balance = 470");
    }

    public static void testZeroWeightShipping() {
        Product air = new ShippableProduct("Helium Balloon", 20, 10, 0);
        Customer customer = new Customer(100);
        Cart cart = customer.cart;

        cart.add(air, 1);
        Shop.checkout(cart, customer);
        System.out.println("Expected: Shipment weight 0g, Subtotal = 20, Shipping = 0, Balance = 80");
    }

    public static void testExactBalance() {
        Product book = new ShippableProduct("Book", 70, 5, 400.0);
        Customer customer = new Customer(100); // 70 + 30

        customer.cart.add(book, 1);
        Shop.checkout(customer.cart, customer);
        System.out.println("Expected: Successful checkout. Balance = 0");
    }
}
