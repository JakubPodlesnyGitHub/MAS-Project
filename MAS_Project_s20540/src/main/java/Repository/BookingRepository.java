package Repository;

import Enums.BookingStatus;
import Model.Booking;
import Model.Person;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BookingRepository {
    private SessionFactory sessionFactory;

    public BookingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void bookTickets(Booking booking) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(booking);
        session.getTransaction().commit();
        session.close();
    }

    public List<Booking> getBookings() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getPersonsSQL = "FROM Booking ";
        List<Booking> bookings = null;
        try {
            bookings = session.createQuery(getPersonsSQL, Booking.class).list();
        } catch (NoResultException ignored) {
        }
        session.getTransaction().commit();
        session.close();
        return bookings;
    }

    public int updateBookingStatus(Booking booking, BookingStatus newBookingStatus) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String updatePasswordSQL = "UPDATE Booking SET bookingStatus = :newBookingStatus WHERE bookingId = :bookingID";
        int result = 0;
        try {
            result = session.createQuery(updatePasswordSQL, Person.class)
                    .setParameter("newBookingStatus", String.valueOf(newBookingStatus))
                    .setParameter("bookingID", booking.getBookingId())
                    .executeUpdate();
        } catch (Exception ignored) {
        }
        System.out.println("Update Booking Status Rows Affected: " + result);
        session.getTransaction().commit();
        session.close();
        booking.setBookingStatus(newBookingStatus);
        return result;
    }
}
