/*
 * Created by JFormDesigner on Mon Jun 20 12:49:40 CEST 2022
 */

package GUI.DisplayReservationTickets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GUI.OtherGUI.GuiHelper;
import Model.Booking;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class DisplayReservationTickets extends JFrame {
    private RepositoryManager repositoryManager;
    private Booking booking;

    private String[] columnNames = {"Ticket Number", "Ticket Price", "Ticket Type", "Seat Number", "Seat Row", "Seat Type"};

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelReservationTitle;
    private JLabel labelIdReservation;
    private JLabel labelReservationIdInput;
    private JLabel labelBookingDate;
    private JLabel labelBookingDateReservationInput;
    private JLabel labelReservationPrice;
    private JLabel labelPriceInput;
    private JLabel llabelBookingStatus;
    private JLabel labelStatusInput;
    private JLabel labelBoughtPlaces;
    private JScrollPane scrollPane1;
    private DefaultTableModel defaultTableModel;
    private JTable table1;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public DisplayReservationTickets(RepositoryManager repositoryManager, Booking booking) {
        this.booking = booking;
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void windowSettings() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(this.getClass().getSimpleName());
        this.pack();
        this.setVisible(true);
    }

    private void buttonOkHandler(ActionEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelReservationTitle = new JLabel();
        labelIdReservation = new JLabel();
        labelReservationIdInput = new JLabel(String.valueOf(booking.getBookingId()));
        labelBookingDate = new JLabel();
        labelBookingDateReservationInput = new JLabel(String.valueOf(booking.getBookingDate()));
        labelReservationPrice = new JLabel();
        labelPriceInput = new JLabel(String.valueOf(booking.getFinalPrice()));
        llabelBookingStatus = new JLabel();
        labelStatusInput = new JLabel(String.valueOf(booking.getBookingStatus()));
        labelBoughtPlaces = new JLabel();
        scrollPane1 = new JScrollPane();
        defaultTableModel = new DefaultTableModel(repositoryManager.getTicketRepository().getDataToTable(booking), columnNames);
        table1 = new JTable(defaultTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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

                //---- labelReservationTitle ----
                labelReservationTitle.setText("Reservation");
                labelReservationTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                contentPanel.add(labelReservationTitle, "cell 0 0 5 1");

                //---- labelIdReservation ----
                labelIdReservation.setText("IdReservation");
                contentPanel.add(labelIdReservation, "cell 0 1 2 1");
                contentPanel.add(labelReservationIdInput, "cell 2 1 6 1");

                //---- labelBookingDate ----
                labelBookingDate.setText("Booking Date");
                contentPanel.add(labelBookingDate, "cell 0 2 2 1");
                contentPanel.add(labelBookingDateReservationInput, "cell 2 2 6 1");

                //---- labelReservationPrice ----
                labelReservationPrice.setText("Price");
                contentPanel.add(labelReservationPrice, "cell 0 3 2 1");
                contentPanel.add(labelPriceInput, "cell 2 3 6 1");

                //---- llabelBookingStatus ----
                llabelBookingStatus.setText("Status");
                contentPanel.add(llabelBookingStatus, "cell 0 4 2 1");
                contentPanel.add(labelStatusInput, "cell 2 4 6 1");

                //---- labelBoughtPlaces ----
                labelBoughtPlaces.setText("Bought Places");
                labelBoughtPlaces.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                contentPanel.add(labelBoughtPlaces, "cell 0 5 3 1");

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1, "cell 0 6 16 1");
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
                okButton.setText("OK");
                okButton.addActionListener(e -> buttonOkHandler(e));
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
