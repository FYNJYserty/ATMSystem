package System.forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame {

    public LogIn() {
        // Настройки формы
        JFrame LogIn = new JFrame("Log in");
        LogIn.setSize(500, 320);

        // Создание элементов
        JLabel loginLabel = new JLabel("ID");
        loginLabel.setBounds(100, 50, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 100, 100, 30);

        JLabel bankLabel = new JLabel("Bank");
        bankLabel.setBounds(100, 150, 100, 30);

        JTextField loginField = new JTextField(15);
        loginField.setBounds(200, 50, 200, 30);

        JTextField passwordField = new JTextField(15);
        passwordField.setBounds(200, 100, 200, 30);

        JTextField bankField = new JTextField(15);
        bankField.setBounds(200, 150, 200, 30);

        JButton LogInButton = new JButton("Log in");
        LogInButton.setBounds(200, 200, 150, 30);

        // Добавление элементов в форму
        LogIn.add(loginLabel);
        LogIn.add(passwordLabel);
        LogIn.add(bankLabel);
        LogIn.add(loginField);
        LogIn.add(passwordField);
        LogIn.add(bankField);
        LogIn.add(LogInButton);

        // Скрипты
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    ATMForm.isOpenLogin = false;
                    LogIn.setVisible(false);
                    ATMForm.stage = 2;
                    ATMForm.uid = loginField.getText();
                    ATMForm.password = passwordField.getText();
                    ATMForm.bank = bankField.getText();
                    LogIn.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!");
                }
            }
        });

        // Настройки формы
        LogIn.setLayout(null);
        LogIn.setVisible(true);
    }
}
