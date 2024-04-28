package System.bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    // First name
    private String firstName;
    // Last name
    private String lastName;
    // User id
    private String uid;
    // The MD5 hash of the user's pin code
    private byte pinHash[];
    // The list of accounts for this user
    private ArrayList<Account> accounts;

    // Creating new user
    public User(String firstName, String lastName, String pinCode, Bank bank) {

        // Giving first and last name for the user
        this.firstName = firstName;
        this.lastName = lastName;

        // Giving the MD5 hash of the user's pin code for security
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pinCode.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // Giving new ID for the user
        this.uid = Bank.getNewUserUID();

        // Create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // Print log message
        System.out.printf("New user %s, %s with ID %s was created!", lastName, firstName, this.uid);
    }

    public void addAccount (Account acc) {
        this.accounts.add(acc);
    }

    public String getUID () {
        return this.uid;
    }

    public boolean validatePin (String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }
}
