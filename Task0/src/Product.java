/**
 * Represents a general product in the e-commerce system.
 * <p>
 * Each product has a name, price, and available quantity.
 * This abstract class provides common behavior for all products
 * and requires subclasses to define whether they are expirable or shippable or both.
 */
abstract public class Product {
    /**
     * The product name in English.
     */
    private String name;
    /**
     * The price per unit of the product in EGP.
     */
    private double price;
    /**
     * The quantity of the product available in stock.
     */
    private int quantity;

    /**
     * Constructs a product with the specified name, price, and quantity.
     *
     * @param name     the product's name
     * @param price    the price per unit
     * @param quantity the quantity in stock
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if price or quantity is negative
     */
    public Product(String name, double price, int quantity) {
        if (name == null) throw new NullPointerException("Product name cannot be null.");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the product name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price per unit.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the current available quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity available in stock.
     * @param quantity the new quantity
     * @throws IllegalArgumentException when quantity is negative
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantity = quantity;
    }

    /**
     * Indicates whether this product is expirable.
     *
     * @return true if expirable, false otherwise
     */
    public abstract boolean isExpirable();

    /**
     * Indicates whether this product requires shipping.
     *
     * @return true if shippable, false otherwise
     */
    public abstract boolean isShippable();
}
