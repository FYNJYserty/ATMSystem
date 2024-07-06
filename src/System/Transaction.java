package System;

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

    /**
     * Constructor
     * @param amount
     * @param fromAccount
     */
    public Transaction (double amount, Account fromAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Constructor
     * @param amount
     * @param memo
     * @param fromAccount
     */
    public Transaction (double amount, String memo, Account fromAccount) {
        this(amount, fromAccount);
        this.memo = memo;
    }

    /**
     * Getting amount
     * @return
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Getting summary
     * @return
     */
    public String getSummaryLine () {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        }
        else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
        }
    }
}
