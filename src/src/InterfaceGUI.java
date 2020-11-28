package src;

import src.dao.LoginDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGUI implements ActionListener
{
    //Login page
    private static JFrame frame;
    private static JPanel loginPanel;
    private static JLabel emailLabel;
    private static JLabel passwordLabel;
    private static JPasswordField passwordTextField;
    private static JTextField emailTextField;
    private static JButton buttonLogin;
    private static JLabel errorLabel;

    //Menu page
    private static JPanel menuPanel;
    private static JLabel menuLabel;
    private static JButton buttonBackLogin;

    //Register page
    private static JPanel registerPanel;

    //MySQL global variable
    private static String password;
    private static String email;

    private static LoginDAO loginDAO;

    public InterfaceGUI(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public static void GUI()
    {
        frame = new JFrame();
        loginPanel = new JPanel();
        menuPanel = new JPanel();
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(loginPanel,BorderLayout.CENTER);
        frame.add(menuPanel,BorderLayout.PAGE_END);



        LoginPageGUI();
        MenuPageGUI();

        loginPanel.setVisible(true);
        menuPanel.setVisible(false);

        frame.setVisible(true);
    }

    public static void LoginPageGUI()
    {

        loginPanel.setLayout(null);

        loginPanel.setVisible(true);
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(100,80,80,25);
        loginPanel.add(emailLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100,140,80,25);
        loginPanel.add(passwordLabel);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(200,140, 150,25);
        loginPanel.add(passwordTextField);

        emailTextField = new JTextField();
        emailTextField.setBounds(200,80,150,25);
        loginPanel.add(emailTextField);

        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(225,200,100,25);
        buttonLogin.addActionListener(new InterfaceGUI(loginDAO));
        loginPanel.add(buttonLogin);

        errorLabel = new JLabel();
        errorLabel.setBounds(198,175,200,25);
        errorLabel.setForeground(Color.red);
        loginPanel.add(errorLabel);

        //frame.setVisible(true);

    }

    public static void MenuPageGUI()
    {

        menuPanel.setLayout(null);

        menuLabel = new JLabel("MENU");
        menuLabel.setBounds(100,80,80,25);
        menuPanel.add(menuLabel);

        buttonBackLogin = new JButton("Back to login");
        buttonBackLogin.setBounds(225,200,150,25);
        buttonBackLogin.addActionListener(new InterfaceGUI(loginDAO));
        menuPanel.add(buttonBackLogin);

        frame.setVisible(true);

    }

    public static void RegisterPageGUI()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == buttonLogin)
        {
            password = passwordTextField.getText();
            email = emailTextField.getText();

            if(loginDAO.getLoginByEmailAndPassword(email,password).getEmail() != null)
            {
                System.out.println("connection...");
                errorLabel.setText("");

                loginPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
            else
            {
                errorLabel.setText("There is no existing account");
            }


        }
        else if(e.getSource() == buttonBackLogin)
        {
            loginPanel.setVisible(true);
            menuPanel.setVisible(false);
        }
    }
}
