package System.bank.forms;

import System.bank.Account;
import System.bank.Bank;
import System.bank.User;

import javax.swing.*;
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

        JButton NewAccountButton = new JButton("New Account");
        NewAccountButton.setBounds(5, 5, 100, 30);

        JButton DoButton = new JButton("Do");
        DoButton.setBounds(1100, 5, 50, 30);

        JLabel NameLabel = new JLabel("Name");
        NameLabel.setBounds(250, 5, 150, 30);

        JLabel LoginLabel = new JLabel("UID");
        LoginLabel.setBounds(350, 5, 150, 30);
        // Добавление элементов в форму
        ATMFrame.add(NewAccountButton);
        ATMFrame.add(LogInButton);
        ATMFrame.add(DoButton);
        ATMFrame.add(NameLabel);
        ATMFrame.add(LoginLabel);

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
    }

    public static void checkUser(String userID, String pin) {

    }

    public static void showTransHistory (User theUser) {
        int theAcct;

    }
}
