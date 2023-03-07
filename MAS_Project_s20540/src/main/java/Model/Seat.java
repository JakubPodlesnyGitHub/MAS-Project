package Model;

import Enums.CinemaSeatType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Seat")
@Access(AccessType.FIELD)
public class Seat {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idSeat;
    private int seatNumber;
    private int row;
    @Transient
    private boolean isTaken;
    @Enumerated(EnumType.STRING)
    @Column(name = "Cinema Seat Type", nullable = false)
    private CinemaSeatType cinemaSeatType;

    //connections
    @OneToMany(mappedBy = "seat",fetch =FetchType.EAGER)
    private List<Ticket> purchasedTickets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CinemaHallID")
    private CinemaHall cinemaHall;

    public Seat() {

    }

    public Seat(int seatNumber, int row, CinemaSeatType cinemaSeatType) throws Exception {
        setSeatNumber(seatNumber);
        setRow(row);
        setCinemaSeatType(cinemaSeatType);
    }

    public Long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public CinemaSeatType getCinemaSeatType() {
        return cinemaSeatType;
    }

    public void setCinemaSeatType(CinemaSeatType cinemaSeatType) throws Exception {
        if (cinemaSeatType == null)
            throw new Exception("Cinema Seat Type cannot be null. This filed is mandatory");
        this.cinemaSeatType = cinemaSeatType;
    }

    //connections
    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<Ticket> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public void addTicketToSeat(Ticket newTicket) {
        if (!getPurchasedTickets().contains(newTicket)) {
            getPurchasedTickets().add(newTicket);
            newTicket.setSeat(this);
        }
    }

    public void removeTicketFromSeat(Ticket ticketToRemove) {
        if (getPurchasedTickets().contains(ticketToRemove)) {
            getPurchasedTickets().remove(ticketToRemove);
            ticketToRemove.setSeat(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seat Number: ").append(getSeatNumber()).append("\n");
        sb.append("Row: ").append(getRow()).append("\n");
        sb.append("Seat Type: ").append(getCinemaSeatType()).append("\n");
        return sb.toString();
    }
}
