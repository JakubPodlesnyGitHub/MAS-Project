package Model;

import Enums.TicketType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Random;

@Entity(name = "Ticket")
@Access(AccessType.FIELD)
public class Ticket {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idTicket;
    @Column(name = "TicketID", nullable = false, unique = true)
    private int ticketID;

    @Enumerated(EnumType.STRING)
    @Column(name = "Ticket Type", nullable = false)
    private TicketType ticketType;
    @Column(name = "Ticket Price", nullable = false)
    private double ticketPrice;

    //connections
    @ManyToOne
    @JoinColumn(name = "Booking ID")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "Cinema Screening")
    private CinemaScreening cinemaScreening;
    @ManyToOne
    @JoinColumn(name = "SeatID")
    private Seat seat;

    public Ticket() {

    }

    public Ticket(TicketType ticketType) throws Exception {
        setTicketID(new Random().nextInt(1000));
        setTicketType(ticketType);
        setTicketPrice(ticketType.getTicketPrice());
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) throws Exception {
        if (ticketType == null)
            throw new Exception("Ticket Type cannot be null. This filed is mandatory");
        this.ticketType = ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    //connections

    public Booking getBooking() {
        return booking;
    }

    public CinemaScreening getCinemaScreening() {
        return cinemaScreening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setBooking(Booking newBooking) {
        if (newBooking.equals(this.booking))
            return;
        if (this.booking != null)
            this.booking.removeTicketFromReservation(this);
        this.booking = newBooking;
        newBooking.addTicketToReservation(this);
    }

    public void setSeat(Seat newSeat) {
        if (newSeat.equals(this.seat))
            return;
        if (this.seat != null)
            this.seat.removeTicketFromSeat(this);
        this.seat = newSeat;
        newSeat.addTicketToSeat(this);
    }

    public void setCinemaScreening(CinemaScreening newCinemaScreening) {
        if (newCinemaScreening.equals(this.cinemaScreening))
            return;
        if (this.cinemaScreening != null)
            this.cinemaScreening.removeTicketFromScreening(this);
        this.cinemaScreening = newCinemaScreening;
        newCinemaScreening.addTicketToScreening(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ticket Id: ").append(getTicketID()).append("\n");
        stringBuilder.append("Ticket Type: ").append(getTicketType()).append("\n");
        stringBuilder.append("Ticket Price: ").append(getTicketPrice()).append("\n");
        return stringBuilder.toString();
    }
}
