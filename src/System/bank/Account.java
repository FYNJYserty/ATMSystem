package System.bank;
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

    public Account (String name, User holder, Bank bank) {
        // Giving a name for the account
        this.name = name;
        this.holder = holder;

        // Giving new UID for this account
        this.uid = bank.getNewAccountUID();

        // Init transactions
        this.transactions = new ArrayList<Transaction>();

    }

    public String getUID () {
        return this.uid;
    }

    public String getSummaryLine() {
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uid, balance, this.name);
        }
        else {
            return String.format("%s : $(%.02f) : %s", this.uid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }
}
