package System.bank.forms;

import System.bank.Account;
import System.bank.Bank;
import System.bank.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMForm extends JFrame {

    // Переменные
    public static boolean isOpenLogin = false;
    public static String name;
    public static String surname;
    public static String password;
    public static String bank;
    public static String uid;
    public static int stage;

    public ATMForm() {
        // Настройки формы
        JFrame ATMFrame = new JFrame("ATM");
        ATMFrame.setSize(1200, 800);

        // Элементы
        JButton LogInButton = new JButton("LogIn");
        LogInButton.setBounds(105, 5, 100, 30);

        JButton NewAccountButton = new JButton("New Acc");
        NewAccountButton.setBounds(5, 5, 100, 30);

        JButton DoButton = new JButton("Do");
        DoButton.setBounds(1100, 5, 50, 30);

        JButton transferHistory = new JButton("Trans history");
        transferHistory.setBounds(5, 55, 200, 30);

        JButton withdrawFunds = new JButton("Withdraw funds");
        withdrawFunds.setBounds(5, 105, 200, 30);

        JButton depositFunds = new JButton("Deposit funds");
        depositFunds.setBounds(5, 155, 200, 30);

        JButton transferFunds = new JButton("Transfer funds");
        transferFunds.setBounds(5, 205, 200, 30);

        JLabel NameLabel = new JLabel("Name");
        NameLabel.setBounds(250, 5, 150, 30);

        JLabel LoginLabel = new JLabel("UID");
        LoginLabel.setBounds(350, 5, 150, 30);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(500, 50, 670, 700);
        textArea.setBackground(Color.black);
        // Добавление элементов в форму
        ATMFrame.add(NewAccountButton);
        ATMFrame.add(LogInButton);
        ATMFrame.add(DoButton);
        ATMFrame.add(NameLabel);
        ATMFrame.add(LoginLabel);
        ATMFrame.add(textArea);

        ATMFrame.add(transferHistory);
        ATMFrame.add(withdrawFunds);
        ATMFrame.add(depositFunds);
        ATMFrame.add(transferFunds);

        // Скрипты
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

        DoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (stage) {
                    case 1:
                        createNew(name, surname, password, bank);
                        break;
                    case 2:
                        break;
                }

            }
        });

        transferHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        withdrawFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        depositFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        transferFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // Настройки формы
        ATMFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ATMFrame.setLayout(null);
        ATMFrame.setVisible(true);
    }


    public static void createNew(String name, String surname, String password, String bank) {
        Bank theBank = new Bank(bank);

        User aUser = theBank.addUser(name, surname, password);

        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        uid = aUser.getUID();
    }

    public static void checkUser(String userID, String pin) {

    }

    public static void showTransHistory (User theUser, JTextArea textArea) {
        int theAcct;

        do {
            textArea.append(String.format("Enter the number (1-%d) of the account" +
                    "whose transactions you want to see: ", theUser.numAccounts()));
            theAcct = Integer.parseInt(textArea.getText().substring(textArea.getText().length() - 6));
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                JOptionPane.showMessageDialog(null, "Invalid account. Please try again.");
            }
        }
        while (theAcct < 0 || theAcct >= theUser.numAccounts());

        theUser.printAcctTransHistory(theAcct);
    }
}
