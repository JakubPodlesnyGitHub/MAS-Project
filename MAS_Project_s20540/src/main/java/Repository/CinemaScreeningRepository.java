package Repository;

import Model.CinemaScreening;
import Model.Movie;
import Other.CurrentData;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CinemaScreeningRepository {
    private SessionFactory sessionFactory;

    public CinemaScreeningRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CinemaScreening> getCinemaScreeningsByDateHour(LocalDate screeningDate, LocalTime screeningTime) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getReviewsBasedOnRateSQL = "FROM CinemaScreening";// WHERE screeningDate = :date AND screeningHour = :hour
        List<CinemaScreening> cinemaScreenings = null;
        try {
            cinemaScreenings = session.createQuery(getReviewsBasedOnRateSQL, CinemaScreening.class).list();
        } catch (NoResultException ignored) {
        }
        //setParameter("date", date)
        //.setParameter("hour", hour)
        //.list();
        session.getTransaction().commit();
        session.close();
        return cinemaScreenings.stream().filter(c -> c.getScreeningHour().equals(screeningTime) && c.getScreeningDate().equals(screeningDate)).collect(Collectors.toList());
    }

    public CinemaScreening getCinemaScreeningByMovieDateTime(Movie movie, LocalTime screeningTime, LocalDate screeningDate) {
        for (CinemaScreening cinemaScreening : CurrentData.cinemaScreeningList) {
            if (cinemaScreening.getMovie().equals(movie) && screeningDate.equals(screeningDate) && screeningTime.equals(screeningTime)) {
                return cinemaScreening;
            }
        }
        return null;
    }
}
