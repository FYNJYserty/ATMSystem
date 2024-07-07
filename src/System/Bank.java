package System;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    // Название банка
    private String bankName;

    // Пользователи банка
    private ArrayList<User> users;

    // Аккаунты банка
    private ArrayList<Account> accounts;

    /**
     * Конструктор
     * @param nameOfBank
     */
    public Bank (String nameOfBank) {
        this.bankName = nameOfBank;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Присвоение нового ID для пользователя
     * @return
     */
    public String getNewUserUID() {
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * Присвоение нового ID для аккаунта
     * @return
     */
    public String getNewAccountUID() {
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * Добавление нового пользователя к банку
     * @param firstName
     * @param lastName
     * @param pin
     * @return
     */
    public User addUser (String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);

        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    /**
     * Проверка регистрации
     * @param userID
     * @param pin
     * @return
     */
    public User userLogin (String userID, String pin) {
        for (User u : this.users) {
            if (u.getUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Добавление нового аккаунта к банку
     * @param acc
     */
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    /**
     * Геттер названия банка
     * @return
     */
    public String getName() {
        return this.bankName;
    }
}
