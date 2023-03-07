package Repository;

import Model.MovieReview;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReviewRepository {
    private SessionFactory sessionFactory;

    public ReviewRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<MovieReview> getReviewsByRate(int rate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getReviewsBasedOnRateSQL = "FROM MovieReview WHERE reviewRate = :reviewRate";
        List<MovieReview> movieReviews = null;
        try {
            movieReviews = session.createQuery(getReviewsBasedOnRateSQL, MovieReview.class).
                    setParameter("reviewRate", rate).list();
        } catch (Exception ignored) {
        }
        session.getTransaction().commit();
        session.close();
        return movieReviews;
    }

    public MovieReview addReview(MovieReview newMovieReview) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newMovieReview);
        session.getTransaction().commit();
        session.close();
        return newMovieReview;
    }
}
