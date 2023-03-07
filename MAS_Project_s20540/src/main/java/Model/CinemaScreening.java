package Model;

import Enums.PersonType;
import GUI.Seats.Seats;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CinemaScreening")
@Access(AccessType.FIELD)
public class CinemaScreening {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idCinemaScreening;
    @Column(name = "Screening Date", nullable = false)
    private LocalDate screeningDate;
    @Column(name = "Screening Hour", nullable = false)
    private LocalTime screeningHour;

    //connections
    @ManyToOne
    //@JoinColumn(name = "MovieID")
    private Movie movie;
    @OneToMany(mappedBy = "cinemaScreening" ,fetch = FetchType.EAGER)
    private List<Ticket> ticketsForScreening = new ArrayList<>();
    @ManyToMany
    private List<Person> employeeList = new ArrayList<>();

    @ManyToOne
    CinemaHall cinemaHall;

    public CinemaScreening() {

    }

    public CinemaScreening(LocalDate screeningDate, LocalTime screeningHour) throws Exception {
        setScreeningDate(screeningDate);
        setScreeningHour(screeningHour);
    }

    public Long getIdCinemaScreening() {
        return idCinemaScreening;
    }

    public void setIdCinemaScreening(Long idCinemaScreening) {
        this.idCinemaScreening = idCinemaScreening;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate screeningDate) throws Exception {
        if (screeningDate == null)
            throw new Exception("Cinema Screening Date cannot be null. This filed is mandatory");
        this.screeningDate = screeningDate;
    }

    public LocalTime getScreeningHour() {
        return screeningHour;
    }

    public void setScreeningHour(LocalTime screeningHour) throws Exception {
        if (screeningHour == null)
            throw new Exception("Cinema Screening Hour cannot be null. This filed is mandatory");
        this.screeningHour = screeningHour;
    }

    //connections
    public List<Seat> getSeats() {
        return getCinemaHall().getSeatsInCinemaHall();
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public List<Person> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Person> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Ticket> getTicketsForScreening() {
        return ticketsForScreening;
    }

    public void setTicketsForScreening(List<Ticket> ticketsForScreening) {
        this.ticketsForScreening = ticketsForScreening;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie newMovie) {
        if (newMovie.equals(this.movie))
            return;
        if (this.movie != null)
            this.movie.removeCinemaScreening(this);
        this.movie = newMovie;
        newMovie.addCinemaScreening(this);
    }

    public void addTicketToScreening(Ticket newTicket) {
        if (!getTicketsForScreening().contains(newTicket)) {
            getTicketsForScreening().add(newTicket);
            newTicket.setCinemaScreening(this);
        }
    }

    public void removeTicketFromScreening(Ticket newTicket) {
        if (getTicketsForScreening().contains(newTicket)) {
            getTicketsForScreening().remove(newTicket);
        }
    }

    public void addEmployeeServiceToScreening(Person newEmployee) throws Exception {
        if (!newEmployee.checkPersonType(PersonType.EMPLOYEE))
            throw new Exception("You do not have access to method: addEmployeeServiceToScreening. Only employee has access.");

        if (!getEmployeeList().contains(newEmployee)) {
            getEmployeeList().add(newEmployee);
            newEmployee.addScreeningServiceToEmployee(this);
        }
    }

    public void setCinemaHall(CinemaHall newCinemaHall) {
        if (newCinemaHall.equals(this.cinemaHall))
            return;
        if (this.cinemaHall != null)
            this.cinemaHall.removeCinemaScreeningFromCinemaHall(this);
        this.cinemaHall = newCinemaHall;
        newCinemaHall.addCinemaScreening(this);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Screening Date").append(getScreeningDate()).append("\n");
        sb.append("Screening Hour").append(getScreeningHour()).append("\n");
        return sb.toString();
    }
}
