package Repository;

import Model.CinemaScreening;
import Model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private SessionFactory sessionFactory;

    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNewMovie(Movie newMovie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newMovie);
        session.getTransaction().commit();
        session.close();
    }

    public int deleteMovie(String movieToDelete) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String movieSQL = "DELETE FROM Movie WHERE title = :movieToDelete ";
        int result = session.createQuery(movieSQL, Movie.class)
                .setParameter("movieToDelete", movieToDelete)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Movie> getMoviesBasedOnDateHourCinemaScreening(List<CinemaScreening> cinemaScreenings) {
        List<Movie> movies = new ArrayList<>();
        if (cinemaScreenings != null || !cinemaScreenings.isEmpty()) {
            cinemaScreenings.stream().forEach(c -> movies.add(c.getMovie()));
        }
        return movies;
    }
}
