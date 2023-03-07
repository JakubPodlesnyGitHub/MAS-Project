/*
 * Created by JFormDesigner on Mon Jun 20 10:41:43 CEST 2022
 */

package GUI.Login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GUI.DateHourChoice.DateHourChoice;
import GUI.OtherGUI.GuiHelper;
import GUI.Register.Register;
import Model.Person;
import Other.CurrentData;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Login extends JFrame {
    private RepositoryManager repositoryManager;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelLogin;
    private JTextField textFieldLogin;
    private JLabel labelPassword;
    private JPasswordField passwordField;
    private JButton buttonForgetPassword;
    private JPanel buttonBar;
    private JButton buttonSingIN;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public Login(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void singIN(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(textFieldLogin.getText());
        System.out.println(String.valueOf(passwordField.getPassword()));
        Person p = repositoryManager.getPersonRepository().getPersonByLoginPassword(textFieldLogin.getText(), String.valueOf(passwordField.getPassword()));
        if (p != null) {
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "The login and password are correct.\nYou may proceed", "Correct login and password", JOptionPane.INFORMATION_MESSAGE);
            CurrentData.person = p;
            DateHourChoice dateHourChoice = new DateHourChoice(repositoryManager);
        } else {
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "There is no client with such data in the system\nYou are redirected to registration.", "Incorrect Login Or Password", JOptionPane.INFORMATION_MESSAGE);
            Register register = new Register(repositoryManager);
        }
    }

    private void forgetPassword(ActionEvent e) {
        String command = e.getActionCommand();
        String login = JOptionPane.showInputDialog("Enter your login");
        String password = repositoryManager.getPersonRepository().getPersonPassword(login);
        if (password != null)
            JOptionPane.showMessageDialog(this, "Your password is: " + password, "Password Info", JOptionPane.INFORMATION_MESSAGE);
        else {
            JOptionPane.showMessageDialog(this, "There is no user with entered login: " + login, "Password Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelLogin = new JLabel();
        textFieldLogin = new JTextField();
        labelPassword = new JLabel();
        passwordField = new JPasswordField();
        buttonForgetPassword = new JButton();
        buttonBar = new JPanel();
        buttonSingIN = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                        "insets dialog,hidemode 3",
                        // columns
                        "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- labelLogin ----
                labelLogin.setText("Login");
                contentPanel.add(labelLogin, "cell 0 1 2 1");
                contentPanel.add(textFieldLogin, "cell 2 1 8 1");

                //---- labelPassword ----
                labelPassword.setText("Password");
                contentPanel.add(labelPassword, "cell 0 2");
                contentPanel.add(passwordField, "cell 2 2 8 1");

                //---- buttonForgetPassword ----
                buttonForgetPassword.setText("Forget Password");
                buttonForgetPassword.setPreferredSize(new Dimension(100, 15));
                buttonForgetPassword.setMaximumSize(new Dimension(152, 20));
                buttonForgetPassword.setMargin(new Insets(5, 0, 0, 0));
                buttonForgetPassword.setMinimumSize(new Dimension(120, 20));
                buttonForgetPassword.setActionCommand("Forget Password");
                buttonForgetPassword.addActionListener(e -> forgetPassword(e));
                contentPanel.add(buttonForgetPassword, "cell 0 3 4 1");
            }
            dialogPane.add(contentPanel, BorderLayout.WEST);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                        "insets dialog,alignx right",
                        // columns
                        "[button,fill]",
                        // rows
                        null));

                //---- buttonSingIN ----
                buttonSingIN.setText("SING IN");
                buttonSingIN.addActionListener(e -> singIN(e));
                buttonBar.add(buttonSingIN, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
