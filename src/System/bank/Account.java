package System.bank;
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
        this.uid = Bank.getNewAccountUID();

        // Init transactions
        this.transactions = new ArrayList<Transaction>();

    }

    public String getUID () {
        return this.uid;
    }
}
