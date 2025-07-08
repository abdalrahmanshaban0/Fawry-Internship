public class PaperBook extends Book {
    private int stock;
    private boolean shippable;

    public PaperBook(String id, String name, double price, int yearPublished, int stock, boolean isShippable) {
        super(id, name, price, yearPublished);
        this.stock = stock;
        this.shippable = isShippable;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isShippable() {
        return shippable;
    }

    public void setShippable(boolean shippable) {
        this.shippable = shippable;
    }

    @Override
    public boolean isForSale() {
        return true;
    }
}
