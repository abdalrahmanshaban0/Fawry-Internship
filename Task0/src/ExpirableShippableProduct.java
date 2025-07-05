import java.time.LocalDate;

/**
 * Represents a product that is both expirable and shippable.
 * <p>
 * This class combines the behavior of {@link ExpirableProduct} and {@link Shippable},
 * making it suitable for items like watermelon and meat.
 */
public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {
    /**
     * The weight of the product in grams.
     */
    private final double weight;

    /**
     * Constructs a new expirable, shippable product.
     *
     * @param name       the name of the product
     * @param price      the price per unit
     * @param quantity   the available stock quantity
     * @param expiryDate the date the product expires
     * @param weight     the weight of the product in grams
     * @throws IllegalArgumentException if price, quantity, or weight is negative
     * @throws NullPointerException     if name or expiryDate is null
     */
    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    /**
     * Indicates that this product is shippable.
     *
     * @return true
     */
    @Override
    public boolean isShippable() {
        return true;
    }

    /**
     * Returns the weight of the product in grams.
     *
     * @return the weight
     */
    @Override
    public double getWeight() {
        return weight;
    }
}
