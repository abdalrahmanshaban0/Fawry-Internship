/**
 * Represents a customer who can have a balance used for purchases.
 */
public class Customer {
    private double balance;
    public Cart cart = new Cart();

    /**
     * Constructs a new customer with initial balance.
     *
     * @param balance the initial balance
     */
    public Customer(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the customer's current balance.
     *
     * @return The balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the customer balance.
     *
     * @param balance the new customer balance.
     */
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }
}
