public abstract class Book extends Product {
    private final int yearPublished;

    public Book(String id, String name, double price, int yearPublished) {
        super(id, name, price);
        this.yearPublished = yearPublished;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Checks if a specific years passed after a book is published
     *
     * @param years years to be checked if passed after book is published
     */
    @Override
    public boolean isOutdated(int years) {
        try {
            int currentYear = java.time.Year.now().getValue();
            return (currentYear - yearPublished) >= years;
        } catch (NumberFormatException e) {
            System.err.println("Invalid year format: " + yearPublished);
            return false;
        }
    }

    public abstract boolean isForSale();
}
