public class DemoBook extends Book {
    public DemoBook(String id, String name, double price, int yearPublished) {
        super(id, name, price, yearPublished);
    }

    @Override
    public boolean isForSale() {
        return false;
    }
}
