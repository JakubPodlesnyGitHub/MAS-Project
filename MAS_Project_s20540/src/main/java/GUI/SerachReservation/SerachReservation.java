/*
 * Created by JFormDesigner on Mon Jun 20 12:46:38 CEST 2022
 */

package GUI.SerachReservation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GUI.DisplayReservationTickets.DisplayReservationTickets;
import GUI.OtherGUI.GuiHelper;
import Model.Booking;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class SerachReservation extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelReservationsTitle;
    private JComboBox comboBoxReservations;
    private JPanel buttonBar;
    private JButton buttonReservation;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private RepositoryManager repositoryManager;

    public SerachReservation(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        addReservationsToComboBox();
        GuiHelper.escActionToCloseWindow(this);
    }

    public void addReservationsToComboBox() {
        for (Booking b : repositoryManager.getBookingRepository().getBookings()) {
            comboBoxReservations.addItem("Booking " + b.getBookingId());
        }
    }

    private void buttonAcceptHandler(ActionEvent e) {
        String command = e.getActionCommand();
        Booking booking = repositoryManager.getBookingRepository().getBookings().get(comboBoxReservations.getSelectedIndex());
        DisplayReservationTickets displayReservationTickets = new DisplayReservationTickets(repositoryManager, booking);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelReservationsTitle = new JLabel();
        comboBoxReservations = new JComboBox();
        buttonBar = new JPanel();
        buttonReservation = new JButton();

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
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));

                //---- labelReservationsTitle ----
                labelReservationsTitle.setText("Reservations");
                labelReservationsTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                contentPanel.add(labelReservationsTitle, "cell 0 0 7 1");
                contentPanel.add(comboBoxReservations, "cell 0 1 10 1");
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

                //---- buttonReservation ----
                buttonReservation.setText("Accept");
                buttonReservation.addActionListener(e -> buttonAcceptHandler(e));
                buttonBar.add(buttonReservation, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
