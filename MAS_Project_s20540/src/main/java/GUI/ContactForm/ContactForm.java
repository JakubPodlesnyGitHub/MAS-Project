/*
 * Created by JFormDesigner on Mon Jun 20 11:35:26 CEST 2022
 */

package GUI.ContactForm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GUI.OtherGUI.GuiHelper;
import GUI.PurchaseConfirmation.PurchaseConfirmation;
import GUI.ReservationConfirmation.ReservationConfirmation;
import Other.CurrentData;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class ContactForm extends JFrame {
    private RepositoryManager repositoryManager;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label5;
    private JLabel label1;
    private JTextField textFieldFirstName;
    private JLabel label2;
    private JTextField textFieldLastName;
    private JLabel label3;
    private JTextField textFieldPhoneNumber;
    private JLabel label4;
    private JTextField textFieldEmail;
    private JPanel buttonBar;
    private JButton buttonCancel;
    private JButton buttonAccept;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public ContactForm(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void buttonCancelHandler(ActionEvent e) {
        GuiHelper.closeAppMethod(this);
    }

    private void setData() throws Exception {
        CurrentData.person.setFirstName(textFieldFirstName.getText());
        CurrentData.person.setLastName(textFieldLastName.getText());
        CurrentData.person.setPhoneNumber(textFieldPhoneNumber.getText());
        CurrentData.person.setEmail(textFieldEmail.getText());
    }

    private void buttonAcceptHandler(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            setData();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        this.setVisible(false);
        ReservationConfirmation reservationConfirmation = new ReservationConfirmation(repositoryManager);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label5 = new JLabel();
        label1 = new JLabel();
        textFieldFirstName = new JTextField();
        label2 = new JLabel();
        textFieldLastName = new JTextField();
        label3 = new JLabel();
        textFieldPhoneNumber = new JTextField();
        label4 = new JLabel();
        textFieldEmail = new JTextField();
        buttonBar = new JPanel();
        buttonCancel = new JButton();
        buttonAccept = new JButton();

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
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- label5 ----
                label5.setText("Contact Form");
                label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                contentPanel.add(label5, "cell 0 1");

                //---- label1 ----
                label1.setText("First Name");
                contentPanel.add(label1, "cell 0 2 2 1");
                contentPanel.add(textFieldFirstName, "cell 2 2 11 1");

                //---- label2 ----
                label2.setText("Last Name");
                contentPanel.add(label2, "cell 0 3 2 1");
                contentPanel.add(textFieldLastName, "cell 2 3 11 1");

                //---- label3 ----
                label3.setText("Phone Number");
                contentPanel.add(label3, "cell 0 4 2 1");
                contentPanel.add(textFieldPhoneNumber, "cell 2 4 11 1");

                //---- label4 ----
                label4.setText("Email");
                contentPanel.add(label4, "cell 0 5 2 1");
                contentPanel.add(textFieldEmail, "cell 2 5 11 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
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
                    "[button,fill]",
                    // rows
                    "[]"));

                //---- buttonCancel ----
                buttonCancel.setText("Cancel");
                buttonCancel.addActionListener(e -> buttonCancelHandler(e));
                buttonBar.add(buttonCancel, "cell 0 0");

                //---- buttonAccept ----
                buttonAccept.setText("Accept");
                buttonAccept.addActionListener(e -> buttonAcceptHandler(e));
                buttonBar.add(buttonAccept, "cell 12 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
