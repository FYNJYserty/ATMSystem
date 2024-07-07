package System.forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccount extends JFrame {

    // Конструктор
    public NewAccount() {
        // Настройки формы
        JFrame NewAcc = new JFrame();
        NewAcc.setSize(500, 400);

        // Создание элементов формы
        JLabel loginLabel = new JLabel("Name"); // Создание нового элемента
        loginLabel.setBounds(100, 50, 100, 30); // Настройки элемента

        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setBounds(100, 100, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 150, 100, 30);

        JLabel bankLabel = new JLabel("Bank");
        bankLabel.setBounds(100, 200, 100, 30);

        JTextField loginField = new JTextField(15);
        loginField.setBounds(200, 50, 200, 30);

        JTextField surnameField = new JTextField(15);
        surnameField.setBounds(200, 100, 200, 30);

        JTextField passwordField = new JTextField(15);
        passwordField.setBounds(200, 150, 200, 30);

        JTextField bankField = new JTextField(15);
        bankField.setBounds(200, 200, 200, 30);

        JButton LogInButton = new JButton("Create new");
        LogInButton.setBounds(200, 250, 150, 30);

        // Добавление элементов в форму
        NewAcc.add(loginLabel);
        NewAcc.add(surnameLabel);
        NewAcc.add(passwordLabel);
        NewAcc.add(bankLabel);
        NewAcc.add(loginField);
        NewAcc.add(surnameField);
        NewAcc.add(passwordField);
        NewAcc.add(bankField);
        NewAcc.add(LogInButton);

        // Скрипты
        // Кнопка регистрации нового аккаунта
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Все поля должны быть заполнены
                if (!loginField.getText().isEmpty() && !surnameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !bankField.getText().isEmpty()) {
                    // Закрытие окна
                    ATMForm.isOpenLogin = false;
                    NewAcc.setVisible(false);
                    // Присвоение полей к статическим переменным
                    ATMForm.name = loginField.getText();
                    ATMForm.surname = surnameField.getText();
                    ATMForm.password = passwordField.getText();
                    ATMForm.bank = bankField.getText();
                    // Стадия регистрации
                    ATMForm.stage = 1;
                    NewAcc.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!");
                }

            }
        });

        // Настройки формы
        NewAcc.setLayout(null);
        NewAcc.setVisible(true);
    }
}
