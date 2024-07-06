package System;

import javax.swing.*;
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
        this.uid = bank.getNewUserUID();

        // Create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // Print log message
        JOptionPane.showMessageDialog(null, "New user " + lastName + ", "
                + firstName + " with ID " + this.uid + " was created!");
        //System.out.printf("New user %s, %s with ID %s was created!", lastName, firstName, this.uid);
    }

    /**
     * Add new account to user
     * @param acc
     */
    public void addAccount (Account acc) {
        this.accounts.add(acc);
    }

    /**
     * Get UID
     * @return
     */
    public String getUID () {
        return this.uid;
    }

    /**
     * Get user's name
     * @return
     */
    public String getName () {
        return this.firstName;
    }

    /**
     * Validate pin
     * @param pin
     * @return
     */
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

    /**
     * Print account's summary
     * @param txtArea
     */
    public void printAccountsSummary(JTextArea txtArea) {
        txtArea.append(String.format("\n%s's accounts summary\n", this.firstName));
        for (int a = 0; a < this.accounts.size(); a++) {
            txtArea.append(String.format("  %d) %s\n", a + 1, this.accounts.get(a).getSummaryLine()));
        }
        txtArea.append("\n");
    }

    /**
     * Get count of user's accounts
     * @return
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transfer history of this account
     * @param acctIdx
     * @param textArea
     */
    public void printAcctTransHistory (int acctIdx, JTextArea textArea) {
        this.accounts.get(acctIdx).printTransHistory(textArea);
    }

    /**
     * Get balance of account
     * @param acctIdx
     * @return
     */
    public double getAcctBalance (int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get account's UID
     * @param acctIdx
     * @return
     */
    public String getAcctUUID (int acctIdx) {
        return this.accounts.get(acctIdx).getUID();
    }

    /**
     * Add transaction to user's account
     * @param acctIdx
     * @param amount
     * @param memo
     */
    public void addAcctTransaction (int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
