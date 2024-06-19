package System.bank;

import javax.swing.*;

public class LogIn {

    public LogIn() {
        JFrame LogIn = new JFrame("Login");
        LogIn.setSize(600, 500);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(100, 100, 100, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 200, 100, 30);

        JTextField loginField = new JTextField(15);
        loginField.setBounds(200, 100, 200, 30);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(200, 200, 200, 30);

        JButton LogInButton = new JButton("LogIn");
        LogInButton.setBounds(200, 300, 100, 30);

        LogIn.add(loginLabel);
        LogIn.add(passwordLabel);
        LogIn.add(loginField);
        LogIn.add(passwordField);
        LogIn.add(LogInButton);

        LogIn.setLayout(null);
        LogIn.setVisible(true);
    }
}
