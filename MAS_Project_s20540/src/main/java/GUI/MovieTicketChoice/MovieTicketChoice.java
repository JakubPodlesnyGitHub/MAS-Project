/*
 * Created by JFormDesigner on Mon Jun 20 11:09:32 CEST 2022
 */

package GUI.MovieTicketChoice;

import Enums.TicketType;
import GUI.DateHourChoice.DateHourChoice;
import GUI.OtherGUI.GuiHelper;
import GUI.Seats.Seats;
import Model.CinemaScreening;
import Model.Movie;
import Model.Ticket;
import Other.CurrentData;
import Repository.RepositoryManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author unknown
 */
public class MovieTicketChoice extends JFrame {
    private RepositoryManager repositoryManager;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelMoviesTitle;
    private JComboBox comboBox1;
    private JLabel labelTicketTitle;
    private JLabel labelTikcetTypeTitle;
    private JLabel labelPriceTitle;
    private JLabel labelAdultTitle;
    private JLabel label1;
    private SpinnerNumberModel spinnerNumberModelAdult;
    private SpinnerNumberModel spinnerNumberModelStudent;
    private JSpinner spinnerAdult;
    private JLabel labelStudentLabel;
    private JLabel label2;
    private JSpinner spinnerStudent;
    private JPanel buttonBar;
    private JButton buttonCancel;
    private JButton buttonProceed;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public MovieTicketChoice(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        addElementsToComboBox();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void addElementsToComboBox() {
        for (Movie movie : CurrentData.moviesList) {
            comboBox1.addItem(movie.getTitle());
        }
    }

    private void setData() {
        CurrentData.movie = CurrentData.moviesList.get(comboBox1.getSelectedIndex());
        CurrentData.cinemaScreening = repositoryManager.getCinemaScreeningRepository().getCinemaScreeningByMovieDateTime(CurrentData.movie, CurrentData.cinemaScreeningTime, CurrentData.cinemaScreeningDate);
        CurrentData.numberAdultTicketsType = (Integer) spinnerAdult.getValue();
        CurrentData.numberStudentTicketsType = (Integer) spinnerAdult.getValue();
    }

    private void proceed(ActionEvent e) {
        String command = e.getActionCommand();
        setData();
        if (CurrentData.numberAdultTicketsType != 0 || CurrentData.numberStudentTicketsType != 0) {
            this.setVisible(false);
            Seats seats = new Seats(repositoryManager);
        } else {
            JOptionPane.showMessageDialog(this, "You can't proceed if you didn't choose tickets.", "Lack of Tickets", JOptionPane.INFORMATION_MESSAGE);
        }
        if (CurrentData.cinemaScreening.getSeats().isEmpty() || CurrentData.cinemaScreening.getSeats() == null) {
            JOptionPane.showMessageDialog(this, "There are no more places for the given screening.\nSelect the date and time of the screening again");
            this.setVisible(false);
            DateHourChoice dateHourChoice = new DateHourChoice(repositoryManager);
        }
    }

    private void cancelHandler(ActionEvent e) {
        GuiHelper.closeAppMethod(this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelMoviesTitle = new JLabel();
        comboBox1 = new JComboBox();
        labelTicketTitle = new JLabel();
        labelTikcetTypeTitle = new JLabel();
        labelPriceTitle = new JLabel();
        labelAdultTitle = new JLabel();
        spinnerNumberModelAdult = new SpinnerNumberModel();
        spinnerNumberModelAdult.setMinimum(0);
        spinnerNumberModelStudent = new SpinnerNumberModel();
        spinnerNumberModelStudent.setMinimum(0);
        label1 = new JLabel(String.valueOf(TicketType.ADULT.getTicketPrice()));
        spinnerAdult = new JSpinner(spinnerNumberModelAdult);
        labelStudentLabel = new JLabel();
        label2 = new JLabel(String.valueOf(TicketType.STUDENT.getTicketPrice()));
        spinnerStudent = new JSpinner(spinnerNumberModelStudent);
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

                //---- labelMoviesTitle ----
                labelMoviesTitle.setText("Movies");
                labelMoviesTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                contentPanel.add(labelMoviesTitle, "cell 0 0 4 2");
                contentPanel.add(comboBox1, "cell 0 2 8 1");

                //---- labelTicketTitle ----
                labelTicketTitle.setText("Ticket Types");
                labelTicketTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                contentPanel.add(labelTicketTitle, "cell 0 4");

                //---- labelTikcetTypeTitle ----
                labelTikcetTypeTitle.setText("Ticket Type");
                contentPanel.add(labelTikcetTypeTitle, "cell 0 5");

                //---- labelPriceTitle ----
                labelPriceTitle.setText("Price");
                contentPanel.add(labelPriceTitle, "cell 1 5");

                //---- labelAdultTitle ----
                labelAdultTitle.setText("Adult");
                contentPanel.add(labelAdultTitle, "cell 0 6");
                contentPanel.add(label1, "cell 1 6");
                contentPanel.add(spinnerAdult, "cell 2 6");

                //---- labelStudentLabel ----
                labelStudentLabel.setText("Student");
                contentPanel.add(labelStudentLabel, "cell 0 7");
                contentPanel.add(label2, "cell 1 7");
                contentPanel.add(spinnerStudent, "cell 2 7");
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
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- buttonCancel ----
                buttonCancel.setText("Cancel");
                buttonCancel.addActionListener(e -> cancelHandler(e));
                buttonBar.add(buttonCancel, "cell 0 0");

                //---- buttonProceed ----
                buttonProceed.setText("Proceed");
                buttonProceed.addActionListener(e -> proceed(e));
                buttonBar.add(buttonProceed, "cell 14 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
