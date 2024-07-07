package System;

import javax.swing.*;
import java.util.ArrayList;

public class Account {
    // Танзакции аккаунта
    private ArrayList<Transaction> transactions;
    // Имя аккаунта
    private String name;
    // ID аккаунта
    private String uid;
    // Владелец аккаунта
    private User holder;

    /**
     * Конструктор
     * @param name
     * @param holder
     * @param bank
     */
    public Account (String name, User holder, Bank bank) {
        // Присвоение имени и владельца аккаунту
        this.name = name;
        this.holder = holder;

        // Присвоение нового ID для аккаунта
        this.uid = bank.getNewAccountUID();

        // Инициализация истории транзакций
        this.transactions = new ArrayList<Transaction>();

    }

    /**
     * Геттер UID аккаунта
     * @return
     */
    public String getUID () {
        return this.uid;
    }

    /**
     * Данные баланса аккаунта
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
     * Геттер баланса
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
     * Вывод истории транзакций аккаунта
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
     * Добавление транзакции к аккаунту
     * @param amount
     * @param memo
     */
    public void addTransaction (double amount, String memo) {
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
