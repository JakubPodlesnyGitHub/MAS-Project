package Other;

import GUI.Seats.Seats;
import Model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CurrentData {
    public static Person person;
    public static LocalDate cinemaScreeningDate;
    public static LocalTime cinemaScreeningTime;
    public static List<CinemaScreening> cinemaScreeningList;
    public static List<Movie> moviesList;
    public static Movie movie;
    public static CinemaScreening cinemaScreening;
    public static int numberAdultTicketsType;
    public static int numberStudentTicketsType;

    public static List<Seat> chosenSeats;

    public static Booking booking;
}
