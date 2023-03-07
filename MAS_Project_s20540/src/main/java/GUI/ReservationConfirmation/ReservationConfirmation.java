/*
 * Created by JFormDesigner on Mon Jun 20 12:17:52 CEST 2022
 */

package GUI.ReservationConfirmation;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.swing.*;

import Enums.BookingStatus;
import Enums.CinemaSeatType;
import Enums.TicketType;
import GUI.OtherGUI.GuiHelper;
import GUI.PurchaseConfirmation.PurchaseConfirmation;
import Model.Booking;
import Model.CinemaScreening;
import Model.Seat;
import Other.CurrentData;
import Repository.BookingRepository;
import Repository.RepositoryManager;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class ReservationConfirmation extends JFrame {
    private RepositoryManager repositoryManager;
    private Booking booking;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel lableWholeCostTitle;
    private JLabel labelCost;

    private DefaultListModel defaultListModelSeats;
    private DefaultListModel defaultListModelTickets;
    private JLabel labelEstimate;
    private JScrollPane scrollPane2;
    private JList listSeats;
    private JScrollPane scrollPane3;
    private JList listTickets;
    private JPanel buttonBar;
    private JButton buttonCancel;
    private JButton buttonProceed;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public ReservationConfirmation(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        booking = new Booking(new Random().nextInt(1000), LocalDate.now(), BookingStatus.PAID);
        try {
            booking.setClient(CurrentData.person);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CurrentData.booking = booking;
        initComponents();
        GuiHelper.windowsSettings(this);
        setSeatsList();
        setTicketsList();
        GuiHelper.escActionToCloseWindow(this);
    }

    private void setSeatsList() {
        int normalCounter = 0;
        int dreamCounter = 0;
        int deluxeCounter = 0;
        for (Seat seat : CurrentData.chosenSeats) {
            if (seat.getCinemaSeatType() == CinemaSeatType.NORMAL) {
                normalCounter++;
            } else if (seat.getCinemaSeatType() == CinemaSeatType.DREAM) {
                dreamCounter++;
            } else {
                deluxeCounter++;
            }
        }
        defaultListModelSeats.addElement("Seat Type - " + CinemaSeatType.NORMAL + " Ticket Amount " + normalCounter + " Price: " + CinemaSeatType.NORMAL.getAdditionalCost());
        defaultListModelSeats.addElement("Seat Type - " + CinemaSeatType.DREAM + " Ticket Amount " + dreamCounter + " Price: " + CinemaSeatType.DREAM.getAdditionalCost());
        defaultListModelSeats.addElement("Seat Type - " + CinemaSeatType.DELUXE + " Ticket Amount " + deluxeCounter + " Price: " + CinemaSeatType.DELUXE.getAdditionalCost());
    }


    public void setTicketsList() {
        defaultListModelTickets.addElement("Ticket Type: "
                + TicketType.ADULT
                + " Amount: "
                + CurrentData.numberAdultTicketsType
                + " Price: "
                + CurrentData.numberAdultTicketsType * TicketType.ADULT.getTicketPrice());
        defaultListModelTickets.addElement("Ticket Type: "
                + TicketType.STUDENT
                + " Amount: "
                + CurrentData.numberStudentTicketsType
                + " Price: "
                + CurrentData.numberStudentTicketsType * TicketType.STUDENT.getTicketPrice());
    }

    private void buttonCancelHandler(ActionEvent e) {
        GuiHelper.windowsSettings(this);
    }

    private void buttonProceedHandler(ActionEvent e) {
        repositoryManager.getBookingRepository().bookTickets(booking);
        JOptionPane.showMessageDialog(this, "Booking " + booking.getBookingId() + " has been created", "Reservation Created", JOptionPane.INFORMATION_MESSAGE);
        try {
            repositoryManager.getTicketRepository().createTickets(booking);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        this.setVisible(false);
        PurchaseConfirmation purchaseConfirmation = new PurchaseConfirmation(repositoryManager);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        lableWholeCostTitle = new JLabel();
        labelCost = new JLabel(String.valueOf(booking.getFinalPrice()));
        labelEstimate = new JLabel();
        scrollPane2 = new JScrollPane();
        defaultListModelSeats = new DefaultListModel();
        defaultListModelTickets = new DefaultListModel();
        listSeats = new JList(defaultListModelSeats);
        scrollPane3 = new JScrollPane();
        listTickets = new JList(defaultListModelTickets);
        buttonBar = new JPanel();
        buttonCancel = new JButton();
        buttonProceed = new JButton();

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
                                "[]"));

                //---- lableWholeCostTitle ----
                lableWholeCostTitle.setText("Total Cost");
                lableWholeCostTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                contentPanel.add(lableWholeCostTitle, "cell 0 0 4 1");
                contentPanel.add(labelCost, "cell 4 0");

                //---- labelEstimate ----
                labelEstimate.setText("Estimate");
                labelEstimate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                contentPanel.add(labelEstimate, "cell 0 2");

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(listSeats);
                }
                contentPanel.add(scrollPane2, "cell 0 4 2 1");

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(listTickets);
                }
                contentPanel.add(scrollPane3, "cell 3 4 3 1");
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
                                "[button,fill]",
                        // rows
                        "[]"));

                //---- buttonCancel ----
                buttonCancel.setText("Cancel");
                buttonCancel.addActionListener(e -> buttonCancelHandler(e));
                buttonBar.add(buttonCancel, "cell 0 0");

                //---- buttonProceed ----
                buttonProceed.setText("Proceed");
                buttonProceed.addActionListener(e -> buttonProceedHandler(e));
                buttonBar.add(buttonProceed, "cell 7 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
