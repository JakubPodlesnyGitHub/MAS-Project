package Repository;

import org.hibernate.SessionFactory;

public class RepositoryManager {
    private BookingRepository bookingRepository;
    private CinemaScreeningRepository cinemaScreeningRepository;
    private MovieRepository movieRepository;
    private PersonRepository personRepository;
    private ReviewRepository reviewRepository;
    private SubscriptionRepository subscriptionRepository;
    private TicketRepository ticketRepository;
    private SeatsRepository seatsRepository;

    public RepositoryManager(SessionFactory sessionFactory) {
        bookingRepository = new BookingRepository(sessionFactory);
        cinemaScreeningRepository = new CinemaScreeningRepository(sessionFactory);
        personRepository = new PersonRepository(sessionFactory);
        reviewRepository = new ReviewRepository(sessionFactory);
        subscriptionRepository = new SubscriptionRepository(sessionFactory);
        ticketRepository = new TicketRepository(sessionFactory);
        movieRepository = new MovieRepository(sessionFactory);
        seatsRepository = new SeatsRepository(sessionFactory);
    }

    public BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public CinemaScreeningRepository getCinemaScreeningRepository() {
        return cinemaScreeningRepository;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public SubscriptionRepository getSubscriptionRepository() {
        return subscriptionRepository;
    }

    public SeatsRepository getSeatsRepository() {
        return seatsRepository;
    }

    public TicketRepository getTicketRepository() {
        return ticketRepository;
    }
}
