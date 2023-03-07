/*
 * Created by JFormDesigner on Mon Jun 20 09:11:00 CEST 2022
 */

package GUI.Register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.ZoneId;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

import Enums.PersonType;
import GUI.DateHourChoice.DateHourChoice;
import GUI.OtherGUI.GuiHelper;
import Model.Person;
import Other.CurrentData;
import Repository.RepositoryManager;
import com.toedter.calendar.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Register extends JFrame {
    private RepositoryManager repositoryManager;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelFirstName;
    private JTextField textFieldFirstName;
    private JLabel lalbelLastName;
    private JTextField textFieldLastName;
    private JLabel labelphoneNumber;
    private JTextField textFiledPhoneNumber;
    private JLabel labelEmail;
    private JTextField textFiledEmail;
    private JLabel labelBirthDate;
    private JCalendar calendar1;
    private JLabel labelLogin;
    private JTextField textFieldLogin;
    private JLabel labelPassword;
    private JPasswordField passwordField;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public Register(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void registerNewPerson(ActionEvent e) {
        String command = e.getActionCommand();
        Person p = null;
        try {
            p = createNewPerson();
            repositoryManager.getPersonRepository().createNewPerson(p);
            this.setVisible(false);
            CurrentData.person = p;
            JOptionPane.showMessageDialog(this, "Welcome" + textFieldFirstName.getText() + " " + textFieldLastName.getText() + ".\nYou may proceed", "Correct login and password", JOptionPane.INFORMATION_MESSAGE);
            DateHourChoice dateHourChoice = new DateHourChoice(repositoryManager);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), ex.getLocalizedMessage(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Person createNewPerson() throws Exception {
        Person client = new Person(
                textFieldFirstName.getText(),
                textFieldLastName.getText(),
                textFiledPhoneNumber.getText(),
                calendar1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                textFiledEmail.getText(),
                textFieldLogin.getText(),
                passwordField.getPassword().toString(),
                Set.of(PersonType.CLIENT)
        );
        client.setClientNumber(new Random().nextInt(1000));
        return client;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelFirstName = new JLabel();
        textFieldFirstName = new JTextField();
        lalbelLastName = new JLabel();
        textFieldLastName = new JTextField();
        labelphoneNumber = new JLabel();
        textFiledPhoneNumber = new JTextField();
        labelEmail = new JLabel();
        textFiledEmail = new JTextField();
        labelBirthDate = new JLabel();
        calendar1 = new JCalendar();
        labelLogin = new JLabel();
        textFieldLogin = new JTextField();
        labelPassword = new JLabel();
        passwordField = new JPasswordField();
        buttonBar = new JPanel();
        okButton = new JButton();

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
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- labelFirstName ----
                labelFirstName.setText("First Name");
                contentPanel.add(labelFirstName, "cell 0 0");
                contentPanel.add(textFieldFirstName, "cell 1 0 4 1");

                //---- lalbelLastName ----
                lalbelLastName.setText("Last Name");
                contentPanel.add(lalbelLastName, "cell 0 1");
                contentPanel.add(textFieldLastName, "cell 1 1 4 1");

                //---- labelphoneNumber ----
                labelphoneNumber.setText("Phone Number");
                contentPanel.add(labelphoneNumber, "cell 0 2");
                contentPanel.add(textFiledPhoneNumber, "cell 1 2 4 1");

                //---- labelEmail ----
                labelEmail.setText("Email");
                contentPanel.add(labelEmail, "cell 0 3");
                contentPanel.add(textFiledEmail, "cell 1 3 4 1");

                //---- labelBirthDate ----
                labelBirthDate.setText("Birth Date");
                contentPanel.add(labelBirthDate, "cell 0 4");
                contentPanel.add(calendar1, "cell 3 4");

                //---- labelLogin ----
                labelLogin.setText("Login");
                contentPanel.add(labelLogin, "cell 0 6");
                contentPanel.add(textFieldLogin, "cell 1 6 4 1");

                //---- labelPassword ----
                labelPassword.setText("Password");
                contentPanel.add(labelPassword, "cell 0 7");
                contentPanel.add(passwordField, "cell 1 7 4 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                        "insets dialog,alignx right",
                        // columns
                        "[button,fill]",
                        // rows
                        null));

                //---- okButton ----
                okButton.setText("SING UP");
                okButton.addActionListener(e -> registerNewPerson(e));
                buttonBar.add(okButton, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
