package System;

import java.util.Date;

public class Transaction {

    // Сумма транзакции
    private double amount;

    // Сообщение для транзакции
    private String memo;

    // Время и дата транзакции
    private Date timestamp;

    // Аккаунт на котором была совершена транзакция
    private Account fromAccount;

    /**
     * Конструктор 1
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
     * Конструктор 2
     * @param amount
     * @param memo
     * @param fromAccount
     */
    public Transaction (double amount, String memo, Account fromAccount) {
        this(amount, fromAccount);
        this.memo = memo;
    }

    /**
     * Геттер суммы транзакции
     * @return
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Вывод информации о транзакции
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
