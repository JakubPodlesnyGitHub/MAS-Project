package Repository;

import Enums.TicketType;
import Model.Booking;
import Model.Seat;
import Model.Ticket;
import Other.CurrentData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private SessionFactory sessionFactory;

    public TicketRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Ticket> getTicketsForReservation(Booking booking) {
        return booking.getTicketsForReservation();
    }

    public Object[][] getDataToTable(Booking booking) {
        List<Ticket> tickets = booking.getTicketsForReservation();
        Object[][] seatsInfo = new Object[tickets.size()][6];
        for (int i = 0; i < tickets.size(); i++) {
            seatsInfo[i] = new Object[]{tickets.get(i).getTicketID(),
                    tickets.get(i).getTicketPrice(),
                    tickets.get(i).getTicketType(),
                    tickets.get(i).getSeat().getSeatNumber(),
                    tickets.get(i).getSeat().getRow(),
                    tickets.get(i).getSeat().getCinemaSeatType()};
        }
        return seatsInfo;
    }

    public void createTickets(Booking booking) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction();
        Ticket ticket;
        for (int i = 0; i < CurrentData.numberStudentTicketsType; i++) {
            ticket = new Ticket(TicketType.STUDENT);
            tickets.add(ticket);
        }
        for (int i = 0; i < CurrentData.numberAdultTicketsType; i++) {
            ticket = new Ticket(TicketType.ADULT);
            tickets.add(ticket);
        }
        for (int i = 0; i < tickets.size(); i++) {
            tickets.get(i).setBooking(booking);
            tickets.get(i).setCinemaScreening(CurrentData.cinemaScreening);
            if(!CurrentData.chosenSeats.get(i).isTaken()){
                tickets.get(i).setSeat(CurrentData.chosenSeats.get(i));
                CurrentData.chosenSeats.get(i).setTaken(true);
            }
            session.persist(tickets.get(i));
        }
        session.getTransaction().commit();
        session.close();
    }
}
