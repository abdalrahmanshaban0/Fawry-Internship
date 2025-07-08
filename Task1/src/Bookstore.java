import java.time.Year;
import java.util.*;

public class Bookstore {
    private final Map<String, Book> books = new HashMap<>();

    public void add(Book book) {
        if(books.containsKey(book.getId())){
            if(book instanceof PaperBook paperBook){
                int newStock = paperBook.getStock();
                PaperBook currentPaperBook = (PaperBook) books.get(book.getId());
                currentPaperBook.setStock(currentPaperBook.getStock() + newStock);
                return;
            }
            else {
                throw new IllegalArgumentException("Book with id=" + book.getId() + " is already existed");
            }
        }
        books.put(book.getId(), book);
    }

    public void remove(Book book) {
        if (book == null) {
            throw new NullPointerException("Null book");
        }

        if (!books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Book not found: " + book.getId());
        }

        books.remove(book.getId());
    }

    public List<Book> removeOutdated(int years) {
        List<Book> removed = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> iterator = books.entrySet().iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next().getValue();
            if(book.isOutdated(years)){
                removed.add(book);
                iterator.remove();
                System.out.println("Removed outdated product -> " + book.getName());
            }
        }
        return removed;
    }

    /**
     * Buy a book
     *
     * @param ISBN     book identifier
     * @param quantity number of books to buy
     * @param address  can be email address if you want to buy EBook or address if it's paperBook
     * @return book price in case of successful buy + shipping fees in case of shippable paperBook (currently 0)
     * @throws IllegalArgumentException if book is not found or book is not for sale or not enough stock
     */
    public double buy(String ISBN, int quantity, String address) {
        if (!books.containsKey(ISBN)) {
            throw new IllegalArgumentException("Book not found: " + ISBN);
        }
        Book book = books.get(ISBN);
        if (!book.isForSale()) {
            throw new IllegalArgumentException("Book is not for sale: " + ISBN);
        }
        if (book instanceof PaperBook paperBook) {
            if (paperBook.getStock() < quantity) {
                throw new IllegalArgumentException("Not enough stock: " + ISBN);
            }
            paperBook.setStock(paperBook.getStock() - quantity);
            if (paperBook.isShippable()) {
                return book.getPrice() * quantity + ShippingService.getInstance().ship(paperBook, address);
            }
        } else {
            MailService.getInstance().sendEBook(book, address);
        }
        return book.getPrice() * quantity;
    }
}
