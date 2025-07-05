/**
 * Represents a product that requires shipping but does not expire.
 * <p>
 * Examples might include electronics, furniture, or other durable goods.
 * Implements the {@link Shippable} interface and extends {@link Product}.
 */
public class ShippableProduct extends Product implements Shippable {
    /**
     * The weight of the product in grams.
     */
    private final double weight;

    /**
     * Constructs a new shippable product with the given details.
     *
     * @param name     the name of the product
     * @param price    the price per unit
     * @param quantity the initial stock quantity
     * @param weight   the weight of the product in grams
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if price, quantity, or weight is negative
     */
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name,price,quantity);
        if (weight < 0) throw new IllegalArgumentException("Weight cannot be negative.");
        this.weight = weight;
    }

    /**
     * Indicates that this product is not expirable.
     *
     * @return false always
     */
    @Override
    public boolean isExpirable() {
        return false;
    }

    /**
     * Indicates that this product is shippable.
     *
     * @return true always
     */
    @Override
    public boolean isShippable() {
        return true;
    }

    /**
     * Returns the weight of the product in grams.
     *
     * @return the product's weight
     */
    @Override
    public double getWeight() {
        return weight;
    }
}
