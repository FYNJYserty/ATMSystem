package System.bank;

import java.util.Date;

public class Transaction {

    // The amount of this transaction
    private double amount;

    // The memo for this transaction
    private String memo;

    // The time and date of this transaction
    private Date timestamp;

    // The account in which the transaction was performed
    private Account fromAccount;

    public Transaction (double amount, Account fromAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    public Transaction (double amount, String memo, Account fromAccount) {
        this(amount, fromAccount);
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }
}
