/**
 * Marker interface for products that require shipping.
 * Provides the name and weight of the item.
 */
public interface Shippable {
    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    String getName();
    /**
     * Returns the weight of the product in grams.
     *
     * @return the product weight
     */
    double getWeight();
}
