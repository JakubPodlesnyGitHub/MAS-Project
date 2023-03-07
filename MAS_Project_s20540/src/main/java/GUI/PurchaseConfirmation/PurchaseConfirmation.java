/*
 * Created by JFormDesigner on Mon Jun 20 12:27:31 CEST 2022
 */

package GUI.PurchaseConfirmation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Enums.BookingStatus;
import GUI.OtherGUI.GuiHelper;
import GUI.SerachReservation.SerachReservation;
import Model.Booking;
import Other.CurrentData;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class PurchaseConfirmation extends JFrame {
    private RepositoryManager repositoryManager;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelTitleConfirmation;
    private JLabel labelFirstName;
    private JLabel labelFirstNameInput;
    private JLabel labelLastName;
    private JLabel labelLastNameInput;
    private JLabel labelPhoneNumber;
    private JLabel labelPhoneNumberInput;
    private JLabel labelEmail;
    private JLabel labelEmailInput;
    private JLabel labelMovieTitle;
    private JLabel labelMovieTitleInput;
    private JLabel labelMovieDate;
    private JLabel labelMovieDateInput;
    private JLabel labelMovieHour;
    private JLabel labelMovieHourInput;
    private JLabel labelPurchaseCost;
    private JLabel labelPurchaseCostInput;
    private JLabel labelPaymentStatus;
    private JLabel labelPaymentStatusInput;
    private JPanel buttonBar;
    private JButton buttonDisplayTickets;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public PurchaseConfirmation(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void buttondisplayTicketsHandler(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            repositoryManager.getBookingRepository().updateBookingStatus(CurrentData.booking, BookingStatus.COMPLETED);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        this.setVisible(false);
        SerachReservation serachReservation = new SerachReservation(repositoryManager);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelTitleConfirmation = new JLabel();
        labelFirstName = new JLabel();
        labelFirstNameInput = new JLabel(CurrentData.person.getFirstName());
        labelLastName = new JLabel();
        labelLastNameInput = new JLabel(CurrentData.person.getLastName());
        labelPhoneNumber = new JLabel();
        labelPhoneNumberInput = new JLabel(CurrentData.person.getPhoneNumber());
        labelEmail = new JLabel();
        labelEmailInput = new JLabel(CurrentData.person.getEmail());
        labelMovieTitle = new JLabel();
        labelMovieTitleInput = new JLabel(CurrentData.cinemaScreening.getMovie().getTitle());
        labelMovieDate = new JLabel();
        labelMovieDateInput = new JLabel(String.valueOf(CurrentData.cinemaScreening.getScreeningDate()));
        labelMovieHour = new JLabel();
        labelMovieHourInput = new JLabel(String.valueOf(CurrentData.cinemaScreening.getScreeningDate()));
        labelPurchaseCost = new JLabel();
        labelPurchaseCostInput = new JLabel(String.valueOf(CurrentData.booking.getFinalPrice()));
        labelPaymentStatus = new JLabel();
        labelPaymentStatusInput = new JLabel(String.valueOf(CurrentData.booking.getBookingStatus()));
        buttonBar = new JPanel();
        buttonDisplayTickets = new JButton();

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
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- labelTitleConfirmation ----
                labelTitleConfirmation.setText("Purchase Confirmation");
                labelTitleConfirmation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                contentPanel.add(labelTitleConfirmation, "cell 0 0 2 1");

                //---- labelFirstName ----
                labelFirstName.setText("First Name");
                contentPanel.add(labelFirstName, "cell 0 1 2 1");
                contentPanel.add(labelFirstNameInput, "cell 2 1 9 1");

                //---- labelLastName ----
                labelLastName.setText("Last Name");
                contentPanel.add(labelLastName, "cell 0 2 2 1");
                contentPanel.add(labelLastNameInput, "cell 2 2 9 1");

                //---- labelPhoneNumber ----
                labelPhoneNumber.setText("Phone Number");
                contentPanel.add(labelPhoneNumber, "cell 0 3 2 1");
                contentPanel.add(labelPhoneNumberInput, "cell 2 3 9 1");

                //---- labelEmail ----
                labelEmail.setText("Email");
                contentPanel.add(labelEmail, "cell 0 4 2 1");
                contentPanel.add(labelEmailInput, "cell 2 4 9 1");

                //---- labelMovieTitle ----
                labelMovieTitle.setText("Movie Title");
                contentPanel.add(labelMovieTitle, "cell 0 5 2 1");
                contentPanel.add(labelMovieTitleInput, "cell 2 5 9 1");

                //---- labelMovieDate ----
                labelMovieDate.setText("MovieDate");
                contentPanel.add(labelMovieDate, "cell 0 6 2 1");
                contentPanel.add(labelMovieDateInput, "cell 2 6 9 1");

                //---- labelMovieHour ----
                labelMovieHour.setText("Movie Hour");
                contentPanel.add(labelMovieHour, "cell 0 7 2 1");
                contentPanel.add(labelMovieHourInput, "cell 2 7 9 1");

                //---- labelPurchaseCost ----
                labelPurchaseCost.setText("Cost");
                contentPanel.add(labelPurchaseCost, "cell 0 8 2 1");
                contentPanel.add(labelPurchaseCostInput, "cell 2 8 9 1");

                //---- labelPaymentStatus ----
                labelPaymentStatus.setText("Payment Status");
                contentPanel.add(labelPaymentStatus, "cell 0 9 2 1");
                contentPanel.add(labelPaymentStatusInput, "cell 2 9 9 1");
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

                //---- buttonDisplayTickets ----
                buttonDisplayTickets.setText("Display Tickets");
                buttonDisplayTickets.addActionListener(e -> buttondisplayTicketsHandler(e));
                buttonBar.add(buttonDisplayTickets, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
