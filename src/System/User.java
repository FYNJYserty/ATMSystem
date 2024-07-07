package System;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    // Имя
    private String firstName;
    // Фамилия
    private String lastName;
    // Пользовательский id
    private String uid;
    // MD5 хеширование пароля пользователя
    private byte pinHash[];
    // Лист аккаунтов для этого пользователя
    private ArrayList<Account> accounts;

    // Конструктор пользователя
    public User(String firstName, String lastName, String pinCode, Bank bank) {

        // Присваиваем имя и фамилию
        this.firstName = firstName;
        this.lastName = lastName;

        // MD5 хеширование пароля для безопасности
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pinCode.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // Присваивание нового ID для пользователя
        this.uid = bank.getNewUserUID();

        // Создание пустого листа для аккаунтов
        this.accounts = new ArrayList<Account>();

        // Сообщение об успешном создании
        JOptionPane.showMessageDialog(null, "New user " + lastName + ", "
                + firstName + " with ID " + this.uid + " was created!");
    }

    /**
     * Добавление новго аккаунта пользователю
     * @param acc
     */
    public void addAccount (Account acc) {
        this.accounts.add(acc);
    }

    /**
     * Геттер UID
     * @return
     */
    public String getUID () {
        return this.uid;
    }

    /**
     * Геттер имени
     * @return
     */
    public String getName () {
        return this.firstName;
    }

    /**
     * Хеширование пароля
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
     * Вывод аккаунтов пользователя
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
     * Геттер количества аккаунтов
     * @return
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * История транзакций
     * @param acctIdx
     * @param textArea
     */
    public void printAcctTransHistory (int acctIdx, JTextArea textArea) {
        this.accounts.get(acctIdx).printTransHistory(textArea);
    }

    /**
     * Геттер баланса аккаунта
     * @param acctIdx
     * @return
     */
    public double getAcctBalance (int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Геттер UID аккаунта
     * @param acctIdx
     * @return
     */
    public String getAcctUUID (int acctIdx) {
        return this.accounts.get(acctIdx).getUID();
    }

    /**
     * Добавление транзакции к аккаунту пользователя
     * @param acctIdx
     * @param amount
     * @param memo
     */
    public void addAcctTransaction (int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
