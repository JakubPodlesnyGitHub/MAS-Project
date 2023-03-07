package Model;

import Enums.BookingStatus;
import Enums.PersonType;
import Enums.TicketType;
import Other.CurrentData;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Booking")
@Access(AccessType.FIELD)
public class Booking {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idBooking;

    @Column(name = "Booking Code", nullable = false, unique = true)
    private int bookingId;

    @Column(name = "Booking Date", nullable = false)
    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Booking Status", nullable = false)
    private BookingStatus bookingStatus;

    //connections
    @OneToMany(mappedBy = "booking",fetch = FetchType.EAGER)
    private List<Ticket> ticketsForReservation = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Person client;

    public Booking() {

    }

    public Booking(int bookingId, LocalDate bookingDate, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    public Long getIdBooking() {
        return idBooking;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingDate(LocalDate bookingDate) throws Exception {
        if (bookingDate == null)
            throw new Exception("Booking Date cannot be null. This filed is mandatory");
        this.bookingDate = bookingDate;
    }

    public void setBookingStatus(BookingStatus bookingStatus) throws Exception {
        if (bookingStatus == null)
            throw new Exception("Booking Status cannot be null. This filed is mandatory");
        this.bookingStatus = bookingStatus;
    }

    //wyliczalne
    @Transient
    public double getFinalPrice() {
        double finalPrice = 0;
        for (Seat seat : CurrentData.chosenSeats) {
            finalPrice += seat.getCinemaSeatType().getAdditionalCost();
        }
        finalPrice += CurrentData.numberStudentTicketsType * TicketType.STUDENT.getTicketPrice()
                + CurrentData.numberStudentTicketsType * TicketType.ADULT.getTicketPrice();
        return finalPrice;
    }

    //connections

    public List<Ticket> getTicketsForReservation() {
        return ticketsForReservation;
    }

    public void setTicketsForReservation(List<Ticket> ticketsForReservation) {
        this.ticketsForReservation = ticketsForReservation;
    }

    public void addTicketToReservation(Ticket newTicket) {
        if (!getTicketsForReservation().contains(newTicket)) {
            getTicketsForReservation().add(newTicket);
            newTicket.setBooking(this);
        }
    }

    public void removeTicketFromReservation(Ticket ticketToRemove) {
        if (getTicketsForReservation().contains(ticketToRemove)) {
            getTicketsForReservation().remove(ticketToRemove);
            ticketToRemove.setBooking(null);
        }
    }

    public void setClient(Person newClient) throws Exception {
        if (!newClient.checkPersonType(PersonType.CLIENT))
            throw new Exception("You do not have access to method: setClient. Only client has access.");

        if (newClient.equals(this.client))
            return;
        if (this.client != null)
            this.client.removeBookingFromClient(this);
        this.client = newClient;
        newClient.addNewBookingToClient(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking Id: ").append(getBookingId()).append("\n");
        sb.append("Booking Date: ").append(getBookingDate()).append("\n");
        sb.append("Booking Status: ").append(getBookingStatus()).append("\n");
        sb.append("Booking Ticket Price: ").append(getFinalPrice()).append("\n");
        return sb.toString();
    }
}
