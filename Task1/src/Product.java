/**
 * A generic type for future types of products in addition to books.
 */
public abstract class Product {
    /**
     * Identifier for each product
     */
    private final String id;
    /**
     * Product name
     */
    private String name;
    /**
     * Product price
     */
    private double price;

    /**
     * Create new product with specific name and price
     *
     * @param name  product name
     * @param price product price
     */
    public Product(String id, String name, double price) {
        if(price < 0){
            throw new IllegalArgumentException("Negative price not permitted");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * returns product Id
     *
     * @return product Id
     */
    public String getId() {
        return id;
    }

    /**
     * Get product price
     *
     * @return Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set product price
     *
     * @param price price of the product
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get product name
     *
     * @return Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product name
     *
     * @param name name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if a product is outdated or not.
     *
     * @param years If specific years is passed after e.g. (production date, publication date, ...)
     *              it will be considered outdated.
     */
    public abstract boolean isOutdated(int years);
}
