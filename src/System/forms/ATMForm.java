package System.forms;

import System.Account;
import System.Bank;
import System.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMForm extends JFrame {

    // Статические переменные
    public static boolean isOpenLogin = false;
    public static String name;
    public static String surname;
    public static String password;
    public static String bank;
    public static String uid;
    public static int stage;

    // Статические объекты классов
    public static User curUser;
    public static Bank curBank;

    // Конструктор
    public ATMForm() {
        // Настройки формы
        JFrame ATMFrame = new JFrame("ATM");
        ATMFrame.setSize(1200, 800);

        // Элементы
        // Кнопки
        JButton LogInButton = new JButton("LogIn"); // Создание элемента
        LogInButton.setBounds(105, 5, 100, 30); // Настройки элемента

        JButton NewAccountButton = new JButton("New Acc");
        NewAccountButton.setBounds(5, 5, 100, 30);

        JButton DoButton = new JButton("Do");
        DoButton.setBounds(1100, 5, 50, 30);

        JButton transferHistory = new JButton("Transfer history");
        transferHistory.setBounds(5, 55, 200, 30);

        JButton withdrawFunds = new JButton("Withdraw");
        withdrawFunds.setBounds(5, 105, 200, 30);

        JButton depositFunds = new JButton("Deposit");
        depositFunds.setBounds(5, 155, 200, 30);

        JButton transferFunds = new JButton("Transfer funds");
        transferFunds.setBounds(5, 205, 200, 30);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(5, 255, 200, 30);

        // Этикетки
        JLabel NameLabel = new JLabel("Name");
        NameLabel.setBounds(250, 5, 150, 30);

        JLabel LoginLabel = new JLabel("UID");
        LoginLabel.setBounds(350, 5, 150, 30);

        // Текстовое поле
        JTextArea textArea = new JTextArea();
        textArea.setBounds(300, 50, 870, 700);
        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);
        textArea.setFont(new Font("Times new Roman", Font.BOLD, 20));

        // Добавление элементов в поле
        ATMFrame.add(NewAccountButton);
        ATMFrame.add(LogInButton);
        ATMFrame.add(DoButton);
        ATMFrame.add(NameLabel);
        ATMFrame.add(LoginLabel);
        ATMFrame.add(textArea);

        ATMFrame.add(transferHistory); // История транзакций
        ATMFrame.add(withdrawFunds); // Вывод средств
        ATMFrame.add(depositFunds); // Депозит средств
        ATMFrame.add(transferFunds); // Перевод средств
        ATMFrame.add(quitButton); // Выход

        // Скрипты
        // Открытие формы для создания аккаунта
        NewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isOpenLogin) {
                    NewAccount NewAccFrame = new NewAccount();
                    NewAccFrame.pack();
                    NewAccFrame.setVisible(true);
                    isOpenLogin = true;
                }
            }
        });

        // Открытие формы для входа в аккаунт
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isOpenLogin) {
                    LogIn LoginFrame = new LogIn();
                    LoginFrame.pack();
                    LoginFrame.setVisible(true);
                    isOpenLogin = true;
                }
            }
        });

        // Кнопка действий
        DoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (stage) {
                    case 1: // Аккаунт зарегистрирован
                        curBank = new Bank(bank);
                        createNew(name, surname, password, curBank);
                        name = null;
                        surname = null;
                        password = null;
                        bank = null;
                        break;
                    case 2: // Выполнен вход
                        curUser = checkUser(uid, password, curBank);
                        NameLabel.setText(curUser.getName());
                        LoginLabel.setText(curUser.getUID());
                        curUser.printAccountsSummary(textArea);
                        break;
                }

            }
        });

        // Вывод истории транзакций
        transferHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                curUser.printAccountsSummary(textArea);
                showTransHistory(curUser, textArea);
            }
        });

        // Вывод средств
        withdrawFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                curUser.printAccountsSummary(textArea);
                withdrawFunds(curUser, textArea);
            }
        });

        // Депозит средств
        depositFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                curUser.printAccountsSummary(textArea);
                depositFunds(curUser, textArea);
            }
        });

        // Перевод средств
        transferFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                curUser.printAccountsSummary(textArea);
                transferFunds(curUser, textArea);
            }
        });

        // Выход из аккаунта
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                NameLabel.setText("Name");
                LoginLabel.setText("UID");
                name = null;
                surname = null;
                password = null;
                bank = null;
                uid = null;
                curUser = null;
                curBank = null;

            }
        });
        // Настройки формы
        ATMFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ATMFrame.setLayout(null);
        ATMFrame.setVisible(true);
    }

    /**
     * Создание нового пользователя
     * @param name
     * @param surname
     * @param password
     * @param theBank
     */
    public static void createNew(String name, String surname, String password, Bank theBank) {

        User aUser = theBank.addUser(name, surname, password);

        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        uid = aUser.getUID();
    }

    /**
     * Проверка существования аккаунта
     * @param userID
     * @param pin
     * @param bank
     * @return
     */
    public static User checkUser(String userID, String pin, Bank bank) {
        User authUser;

        authUser = bank.userLogin(userID, pin);
        if (authUser == null) {
            JOptionPane.showMessageDialog(null, "Incorrect user ID/pin combination\nPlease try again");
        }

        return authUser;
    }

    /**
     * Показать историю транзакций
     * @param theUser
     * @param textArea
     */
    public static void showTransHistory (User theUser, JTextArea textArea) {
        int theAcct;

        do {
            theAcct = Integer.parseInt(JOptionPane.showInputDialog("The number of the account:")) - 1;
            textArea.append(String.format("Enter the number (1-%d) of the account" +
                    " whose transactions you want to see: %d\n", theUser.numAccounts(), theAcct + 1));
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (theAcct < 0 || theAcct >= theUser.numAccounts());

        theUser.printAcctTransHistory(theAcct, textArea);
    }

    /**
     * Процесс перевода средств из одного аккаунта в другой
     * @param theUser
     * @param textArea
     */
    public static void transferFunds(User theUser, JTextArea textArea) {
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        do {
            fromAcct = Integer.parseInt(JOptionPane.showInputDialog("The number of the account to transfer from:")) - 1;
            textArea.append(String.format("Enter the number (1-%d) of the account to transfer from: %d\n", theUser.numAccounts(), fromAcct + 1));
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);

        do {
            toAcct = Integer.parseInt(JOptionPane.showInputDialog("The number of the account to transfer:")) - 1;
            textArea.append(String.format("Enter the number (1-%d) of the account to transfer: %d\n", theUser.numAccounts(), toAcct + 1));
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (toAcct < 0 || toAcct >= theUser.numAccounts());

        do {
            amount = Double.parseDouble(JOptionPane.showInputDialog("The amount of transfer:"));
            textArea.append(String.format("Enter the amount to transfer (max $%.02f) : $ %.02f\n", acctBal, amount));
            if (amount < 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than zero!");
            } else if (amount > acctBal) {
                JOptionPane.showMessageDialog(null, String.format("Amount must not be greater than balance of $%.02f\n", acctBal));
            }
        }
        while (amount < 0 || amount > acctBal);

        theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
                "Transfer to account %s", theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount, String.format(
                "Transfer to account %s", theUser.getAcctUUID(fromAcct)));
    }

    /**
     * Процесс вывода средств из аккаунта
     * @param theUser
     * @param textArea
     */
    public static void withdrawFunds(User theUser, JTextArea textArea) {
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            fromAcct = Integer.parseInt(JOptionPane.showInputDialog("The number of the account to withdraw from:")) - 1;
            textArea.append(String.format("Enter the number (1-%d) of the account to withdraw from: %d\n", theUser.numAccounts(), fromAcct + 1));
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);

        do {
            amount = Double.parseDouble(JOptionPane.showInputDialog("The amount of transfer:"));
            textArea.append(String.format("Enter the amount to transfer (max $%.02f) : $ %.02f\n", acctBal, amount));
            if (amount < 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than zero!");
            } else if (amount > acctBal) {
                JOptionPane.showMessageDialog(null, String.format("Amount must not be greater than balance of $%.02f\n", acctBal));
            }
        }
        while (amount < 0 || amount > acctBal);

        memo = JOptionPane.showInputDialog("Enter a memo:");
        textArea.append(String.format("Enter a memo: %s\n", memo));

        theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }

    /**
     * Процесс депозита средств в аккаунт
     * @param theUser
     * @param textArea
     */
    public static void depositFunds(User theUser, JTextArea textArea) {
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            toAcct = Integer.parseInt(JOptionPane.showInputDialog("The number of the account to deposit in:")) - 1;
            textArea.append(String.format("Enter the number (1-%d) of the account to deposit in: %d\n", theUser.numAccounts(), toAcct + 1));
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (toAcct < 0 || toAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(toAcct);

        do {
            amount = Double.parseDouble(JOptionPane.showInputDialog("The amount of transfer:"));
            textArea.append(String.format("Enter the amount to transfer (max $%.02f) : $ %.02f\n", acctBal, amount));
            if (amount < 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than zero!");
            }
        }
        while (amount < 0);

        memo = JOptionPane.showInputDialog("Enter a memo:");
        textArea.append(String.format("Enter a memo: %s\n", memo));

        theUser.addAcctTransaction(toAcct, amount, memo);
    }
}
