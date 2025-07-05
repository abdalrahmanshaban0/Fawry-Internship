import java.time.LocalDate;

/**
 * Represents a product that has an expiration date.
 */
public class ExpirableProduct extends Product {
    /**
     * Stores the expiration date of the product
     */
    private final LocalDate expiryDate;

    /**
     * Constructs a new expirable product.
     *
     * @param name       the product name
     * @param price      the price
     * @param quantity   available quantity
     * @param expiryDate the expiration date
     */
    ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    /**
     * Overrides parent function to always return true
     *
     * @return true
     */
    @Override
    public boolean isExpirable() {
        return true;
    }

    /**
     * Overrides parent function to always return false
     *
     * @return false
     */
    @Override
    public boolean isShippable() {
        return false;
    }

    /**
     * Checks whether the expiry date is before current date
     *
     * @return true if expired
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
