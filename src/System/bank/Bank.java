package System.bank;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {

    // The bank's title
    private String bankName;

    // The users of this bank
    private ArrayList<User> users;

    // The accounts of this bank
    private ArrayList<Account> accounts;

    public Bank (String nameOfBank) {
        this.bankName = nameOfBank;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public static String getNewUserUID () {
        return UUID.randomUUID().toString();
    }

    public static String getNewAccountUID () {
        return UUID.randomUUID().toString();
    }

    public User createNewUser (String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);

        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public User userLogin (String userID, String pin) {
        for (User u : this.users) {
            if (u.getUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        return null;
    }

    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
}
