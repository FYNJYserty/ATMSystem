package System;

import javax.swing.*;
import java.util.ArrayList;

public class Account {
    // Account transactions
    private ArrayList<Transaction> transactions;
    // Account name
    private String name;
    // Account ID
    private String uid;
    // The owner of this account
    private User holder;

    /**
     * Constructor of account
     * @param name
     * @param holder
     * @param bank
     */
    public Account (String name, User holder, Bank bank) {
        // Giving a name for the account
        this.name = name;
        this.holder = holder;

        // Giving new UID for this account
        this.uid = bank.getNewAccountUID();

        // Init transactions
        this.transactions = new ArrayList<Transaction>();

    }

    /**
     * Gets UID of account
     * @return
     */
    public String getUID () {
        return this.uid;
    }

    /**
     * Summary line
     * @return
     */
    public String getSummaryLine() {
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uid, balance, this.name);
        }
        else {
            return String.format("%s : $(%.02f) : %s", this.uid, balance, this.name);
        }
    }

    /**
     * Getting balance
     * @return
     */
    public double getBalance(){
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * Printing trans history for account
     * @param textArea
     */
    public void printTransHistory (JTextArea textArea) {
        textArea.append(String.format("\nTransaction history for account %s\n", this.uid));

        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            textArea.append(this.transactions.get(t).getSummaryLine() + "\n");
        }
        textArea.append("\n");
    }

    /**
     * Adding transaction to an account
     * @param amount
     * @param memo
     */
    public void addTransaction (double amount, String memo) {
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
