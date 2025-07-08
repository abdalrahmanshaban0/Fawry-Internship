import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bookstore store = new Bookstore();

        // Setup books
        PaperBook p1 = new PaperBook("101", "Java Basics", 150.0, 2015, 5, true);
        PaperBook p2 = new PaperBook("102", "Clean Code", 200.0, 2010, 1, false);
        EBook e1 = new EBook("103", "Effective Java", 100.0, 2018, "pdf");
        DemoBook demo = new DemoBook("104", "Demo Draft", 50.0, 2020);

        // 1. Add books
        store.add(p1);
        store.add(p2);
        store.add(e1);
        store.add(demo);
        System.out.println("Books added successfully.");
        System.out.println();

        // 2. Add duplicate PaperBook (should increase stock)
        store.add(new PaperBook("101", "Java Basics", 150.0, 2015, 1, true));
        System.out.println("After duplicate add, updated stock of p1: " + p1.getStock()); // Should be 6
        System.out.println();

        // 3. Try adding duplicate EBook (should throw exception)
        try {
            store.add(new EBook("103", "Effective Java", 100.0, 2018, "pdf"));
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
        System.out.println();

        // 4. Try buying a paper book with enough stock
        System.out.println("Current 101 stock is: " + p1.getStock());
        double total1 = store.buy("101", 2, "Cairo");
        System.out.println("Bought 2 Java Basics (PaperBook), total price = " + total1); // 2*150 = 300.0
        System.out.println("Current 101 stock is: " + p1.getStock());
        System.out.println();

        // 5. Try buying a paper book with no stock
        try {
            store.buy("102", 2, "Cairo");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage()); // Not enough stock
        }
        System.out.println();

        // 6. Try buying a DemoBook (not for sale)
        try {
            store.buy("104", 1, "Cairo");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage()); // Book not for sale
        }
        System.out.println();

        // 7. Buy an EBook
        double total2 = store.buy("103", 1, "user@example.com");
        System.out.println("Bought 1 Effective Java (EBook), total price = " + total2); // 1*100 = 100.0
        System.out.println();

        // 8. Remove a book
        store.remove(p2);
        System.out.println("Removed book p2 successfully.");
        System.out.println();

        // 9. Try removing a null book
        try {
            store.remove(null);
        } catch (NullPointerException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
        System.out.println();

        // 10. Try removing a book not in store
        try {
            store.remove(p2);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
        System.out.println();

        // 11. Remove outdated books (e.g. older than or equal 10 years)
        List<Book> outdated = store.removeOutdated(10);
        System.out.println("Outdated books removed: " + outdated.size());
        System.out.println();

        // 12. Check isOutdated logic (in 2025)
        System.out.println("Is p1 outdated (2015)? " + p1.isOutdated(5)); // true
        System.out.println("Is e1 outdated (2018)? " + e1.isOutdated(10)); // false
    }
}