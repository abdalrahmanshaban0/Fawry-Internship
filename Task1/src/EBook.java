public class EBook extends Book {
    private String filetype;

    public EBook(String id, String name, double price, int yearPublished, String filetype) {
        super(id, name, price, yearPublished);
        this.filetype = filetype;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    @Override
    public boolean isForSale() {
        return true;
    }
}
