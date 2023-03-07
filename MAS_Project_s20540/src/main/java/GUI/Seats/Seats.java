/*
 * Created by JFormDesigner on Mon Jun 20 11:24:44 CEST 2022
 */

package GUI.Seats;

import GUI.ContactForm.ContactForm;
import GUI.OtherGUI.GuiHelper;
import Model.Seat;
import Other.CurrentData;
import Repository.RepositoryManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown
 */
public class Seats extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY   // GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private DefaultTableModel defaultTableModel;
    private JTable table1;
    private JPanel buttonBar;
    private JButton proceedButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String[] columnNames = {"Seat Number", "Row Number", "Seat Type", "Description", "Additional Cost", ""};

    private RepositoryManager repositoryManager;

    public Seats(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private List<Seat> getSeats() {
        List<Seat> markedSeats = new ArrayList<>();
        for (int i = 0; i < CurrentData.cinemaScreening.getSeats().size(); i++) {
            if (table1.getValueAt(i, 5) == Boolean.TRUE) {
                markedSeats.add(CurrentData.cinemaScreening.getSeats().get(i));
            }
        }
        return markedSeats;
    }

    private int checkNumberOfSelectedCheckBoxes() {
        int counter = 0;
        for (int i = 0; i < CurrentData.cinemaScreening.getSeats().size(); i++) {
            if (table1.getValueAt(i, 5) == Boolean.TRUE) {
                counter++;
            }
        }
        return counter;

    }

    private void buttonProceedHandler(ActionEvent e) {
        if (checkNumberOfSelectedCheckBoxes() != (CurrentData.numberAdultTicketsType + CurrentData.numberStudentTicketsType)) {
            JOptionPane.showMessageDialog(this,
                    "You cannot proceed because you have marked more or less seats (" + checkNumberOfSelectedCheckBoxes() + ") than you have tickets (" + (CurrentData.numberAdultTicketsType + CurrentData.numberStudentTicketsType) + ")",
                    "No coverage between seats and tickets",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.setVisible(false);
            CurrentData.chosenSeats = getSeats();
            ContactForm contactForm = new ContactForm(repositoryManager);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        defaultTableModel = new DefaultTableModel(repositoryManager.getSeatsRepository().getDataToTable(), columnNames);

        table1 = new JTable(defaultTableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0, 1 -> Integer.class;
                    case 2, 3 -> String.class;
                    case 4 -> Double.class;
                    default -> Boolean.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        buttonBar = new JPanel();
        proceedButton = new JButton();

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
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]"));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1, "cell 0 1");
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

                //---- proceedButton ----
                proceedButton.setText("Proceed");
                proceedButton.addActionListener(e -> buttonProceedHandler(e));
                buttonBar.add(proceedButton, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
