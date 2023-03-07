/*
 * Created by JFormDesigner on Mon Jun 20 10:59:30 CEST 2022
 */

package GUI.DateHourChoice;

import GUI.MovieTicketChoice.MovieTicketChoice;
import GUI.OtherGUI.GuiHelper;
import Model.CinemaScreening;
import Model.Movie;
import Other.CurrentData;
import Repository.RepositoryManager;
import com.toedter.calendar.JCalendar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author unknown
 */
public class DateHourChoice extends JFrame {
    private RepositoryManager repositoryManager;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelTitleChooseDateHour;
    private JLabel labelHour;
    private JTextField textFieldMovieHour;
    private JLabel labelDate;
    private JCalendar calendar1;
    private JPanel buttonBar;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public DateHourChoice(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        initComponents();
        GuiHelper.windowsSettings(this);
        GuiHelper.escActionToCloseWindow(this);
    }

    private void setData(){
        CurrentData.cinemaScreeningDate = calendar1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        CurrentData.cinemaScreeningTime = LocalTime.parse(textFieldMovieHour.getText());
        CurrentData.cinemaScreeningList = repositoryManager.getCinemaScreeningRepository().getCinemaScreeningsByDateHour(CurrentData.cinemaScreeningDate, CurrentData.cinemaScreeningTime);
        CurrentData.moviesList = repositoryManager.getMovieRepository().getMoviesBasedOnDateHourCinemaScreening(CurrentData.cinemaScreeningList);
    }

    private void search(ActionEvent e) {
        String command = e.getActionCommand();
        setData();
        if (!CurrentData.moviesList.isEmpty()) {
            this.setVisible(false);
            MovieTicketChoice movieTicketChoice = new MovieTicketChoice(repositoryManager);
        } else {
            JOptionPane.showMessageDialog(this, "There is no movies that day", "No Movies", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelTitleChooseDateHour = new JLabel();
        labelHour = new JLabel();
        textFieldMovieHour = new JTextField();
        labelDate = new JLabel();
        calendar1 = new JCalendar();
        buttonBar = new JPanel();
        buttonSearch = new JButton();

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
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- labelTitleChooseDateHour ----
                labelTitleChooseDateHour.setText("Choose Date And Hour");
                labelTitleChooseDateHour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                contentPanel.add(labelTitleChooseDateHour, "cell 0 0 10 2");

                //---- labelHour ----
                labelHour.setText("Hour");
                contentPanel.add(labelHour, "cell 0 2 2 1");
                contentPanel.add(textFieldMovieHour, "cell 2 2");

                //---- labelDate ----
                labelDate.setText("Date");
                contentPanel.add(labelDate, "cell 0 3");
                contentPanel.add(calendar1, "cell 2 3");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonSearch ----
                buttonSearch.setText("Search");
                buttonSearch.addActionListener(e -> search(e));
                buttonBar.add(buttonSearch);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
