package MockData;

import Enums.*;
import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MockDataFulfillment {
    private Movie movieJurassicWorldDominion, movieTopGunMaverick, movieBuzzAstral;
    private Actor actorChrisEvans, actorPeterSohn, actorTomCruise, actorMilesTeller, actorChrisPratt, actorLauraDern;
    private ActorMovie prattJurassicWorld, dernJurrassicWorld, cruiseTopGun, tellerTopGun, evansBuzzAstral, sohnBuzzAstral;
    private MovieReview jurassicReview1, jurassicReview2, topGunReview1, topGunReview2, buzzAstralReview1, buzzAstralReview2;

    private CinemaHall cinemaHall1, cinemaHall2, cinemaHall3;
    private List<Seat> seats = new ArrayList<>();
    private Booking booking1, booking2;
    private Ticket ticket1, ticket2, ticket3, ticket4, ticket5;

    private CinemaScreening cinemaScreening1, cinemaScreening2, cinemaScreening3;

    private Subscription subscription1, subscription2, subscription3;

    private Person clientMaciek, clientAgata, employeeJakub;

    public MockDataFulfillment() throws Exception {
        createMovies();
        createActors();
        createActorMovies();
        createMoviesReviews();
        createCinemaHall();
        createSeats();
        createTickets();
        createBookings();
        createCinemaScreenings();
        createSubscriptions();
        createPerson();
    }

    private void createMovies() throws Exception {
        movieJurassicWorldDominion = new Movie("Jurassic World Dominion", "Akcja", "Colin Trevorrow", 6, 162, LocalDate.of(2022, 6, 17), MovieAgeCategory.PG_13);
        movieTopGunMaverick = new Movie("Top Gun Maverick", "Akcja", "Joseph Kosinski", 8, 131, LocalDate.of(2022, 5, 26), MovieAgeCategory.R);
        movieBuzzAstral = new Movie("Buzz Astral", "Animacja", "Angus MacLane", 5, 100, LocalDate.of(2022, 6, 17), MovieAgeCategory.PG);
    }

    private void createActors() throws Exception {
        actorChrisEvans = new Actor("Chris", "Evans", LocalDate.of(1981, 6, 13), false, "New York Film Academy");
        actorPeterSohn = new Actor("Peter", "Sohn", LocalDate.of(1977, 8, 18), false, "CalArts");
        actorTomCruise = new Actor("Tom", "Cruise", LocalDate.of(1962, 7, 3), true, "Neighborhood Playhouse School of Theatre");
        actorMilesTeller = new Actor("Miles", "Teller", LocalDate.of(1987, 2, 20), false, "Lee Strasberg Theatre and Film Institute");
        actorChrisPratt = new Actor("Chris", "Pratt", LocalDate.of(1979, 6, 21), false, "Lake Stevens High School");
        actorLauraDern = new Actor("Laura", "Dern", LocalDate.of(1967, 2, 10), true, "University of California");
    }

    private void createActorMovies() throws Exception {
        evansBuzzAstral = new ActorMovie("Buzz Astral", 4, actorChrisEvans, movieBuzzAstral);
        sohnBuzzAstral = new ActorMovie("Buzz Astral", 1, actorPeterSohn, movieBuzzAstral);
        prattJurassicWorld = new ActorMovie("Owen Grady", 1, actorChrisPratt, movieJurassicWorldDominion);
        dernJurrassicWorld = new ActorMovie("Ellie Sattler", 2, actorLauraDern, movieJurassicWorldDominion);
        cruiseTopGun = new ActorMovie("Kapitan Pete 'Maverick' Mitchell", 5, actorTomCruise, movieTopGunMaverick);
        tellerTopGun = new ActorMovie("Pułkownik Bradley 'Rooster' Bradshaw", 3, actorMilesTeller, movieTopGunMaverick);
    }

    private void createMoviesReviews() throws Exception {
        jurassicReview1 = new MovieReview("Jurassic World: Dominion z rykiem wkracza do kin z twórczym wyczerpaniem skubiącym drapieżne szczęki dinozaura. Jedyne poczucie podziwu wiąże się z okropnym rozczarowaniem oraz straconą szansą", 3);
        jurassicReview2 = new MovieReview("Czy fabuła ma sens? Oczywiście nie. Ale efekty są tak imponujące, że nawet ci, którzy mają słabość do tej czcigodnej serii, poczują się usatysfakcjonowani rezultatem.", 6);
        topGunReview1 = new MovieReview("W jakiś sposób powrót Cruise'a do strefy zagrożenia zostanie zapamiętany bardziej niż oryginał, ustanawiając nowy standard w erze restartów.", 8);
        topGunReview2 = new MovieReview("Sequel Top Gun dostarcza akcji i emocji napędzanych nostalgią.", 7);
        buzzAstralReview1 = new MovieReview("Stosunkowo niewielki wysiłek Pixara, w którym występuje Buzz z Toy Story, jest jednak eskapistyczną letnią rozrywką dla rodziny.", 7);
        buzzAstralReview2 = new MovieReview("Mamy wierzyć, że ten film zapoczątkował udaną linię figurek kosmicznych strażników? Ten film?", 3);
        movieJurassicWorldDominion.addReviewToMovie(jurassicReview1);
        movieJurassicWorldDominion.addReviewToMovie(jurassicReview2);
        movieTopGunMaverick.addReviewToMovie(topGunReview1);
        movieTopGunMaverick.addReviewToMovie(topGunReview2);
        movieBuzzAstral.addReviewToMovie(buzzAstralReview1);
        movieBuzzAstral.addReviewToMovie(buzzAstralReview2);
    }

    private void createCinemaHall() throws Exception {
        cinemaHall1 = new CinemaHall("Sala Premierowa", CinemaHallType.PREMIERE, "Dolby Atmos", " Barco DP4K-40LHC", 120);
        cinemaHall2 = new CinemaHall("Sala IMAX", CinemaHallType.IMAX, "Dolby Atmos", " Barco DP4K-40LHC", 160);
        cinemaHall3 = new CinemaHall("Sala 3D", CinemaHallType._3D, "Dolby Digital", " Barco DP4K-40LHC", 100);
    }

    private void createSeats() throws Exception {
        Seat seat = null;
        for (int i = 0, seatNumber = 1; i < 60; i++, seatNumber++) {
            if (i >= 0 && i <= 30) {
                seat = new Seat(seatNumber, 1, CinemaSeatType.NORMAL);
            }
            if (i >= 31 && i <= 45) {
                seat = new Seat(seatNumber, 2, CinemaSeatType.DELUXE);
            }
            if (i >= 46) {
                seat = new Seat(seatNumber, 3, CinemaSeatType.DREAM);
            }
            seats.add(seat);
            seat.setCinemaHall(cinemaHall1);
        }
    }

    private void createTickets() throws Exception {
        ticket1 = new Ticket(TicketType.STUDENT);
        ticket2 = new Ticket(TicketType.ADULT);
        ticket3 = new Ticket(TicketType.STUDENT);
        ticket4 = new Ticket(TicketType.ADULT);
        ticket5 = new Ticket(TicketType.STUDENT);
        ticket1.setSeat(seats.get(4));
        ticket2.setSeat(seats.get(4));
        ticket3.setSeat(seats.get(50));
        ticket4.setSeat(seats.get(40));
        ticket5.setSeat(seats.get(45));
    }

    private void createBookings() {
        booking1 = new Booking(1, LocalDate.of(2022, 6, 15), BookingStatus.PAID);
        booking2 = new Booking(2, LocalDate.of(2022, 6, 10), BookingStatus.PAID);
        booking1.addTicketToReservation(ticket1);
        booking1.addTicketToReservation(ticket2);
        booking1.addTicketToReservation(ticket3);
        booking2.addTicketToReservation(ticket4);
        booking2.addTicketToReservation(ticket5);
    }

    private void createCinemaScreenings() throws Exception {
        cinemaScreening1 = new CinemaScreening(LocalDate.of(2022, 6, 17), LocalTime.of(18, 0));
        cinemaScreening2 = new CinemaScreening(LocalDate.of(2022, 6, 19), LocalTime.of(14, 0));
        cinemaScreening3 = new CinemaScreening(LocalDate.of(2022, 6, 18), LocalTime.of(20, 45));
        cinemaScreening1.addTicketToScreening(ticket1);
        cinemaScreening1.addTicketToScreening(ticket2);
        cinemaScreening2.addTicketToScreening(ticket3);
        cinemaScreening2.addTicketToScreening(ticket4);
        cinemaScreening3.addTicketToScreening(ticket5);
        cinemaScreening1.setMovie(movieJurassicWorldDominion);
        cinemaScreening2.setMovie(movieBuzzAstral);
        cinemaScreening3.setMovie(movieBuzzAstral);
        cinemaScreening1.setCinemaHall(cinemaHall1);
        cinemaScreening2.setCinemaHall(cinemaHall1);
        cinemaScreening3.setCinemaHall(cinemaHall1);
    }

    private void createSubscriptions() throws Exception {
        subscription1 = new Subscription("Sub1", LocalDate.of(2022, 5, 14));
        subscription2 = new Subscription("Sub2", LocalDate.of(2022, 5, 19));
        subscription3 = new Subscription("Sub3", LocalDate.of(2022, 4, 30));
    }

    private void createPerson() throws Exception {
        clientAgata = new Person("Agata", "Nowak", "111222333", LocalDate.of(1999, 3, 1), "agataNowak@gmail.com", "Agata123", "Password123", Set.of(PersonType.CLIENT));
        clientAgata.setClientNumber(1);
        clientMaciek = new Person("Maciek", "Kowalski", "333444555", LocalDate.of(2001, 6, 15), "maciekKowalski@gmail.com", "MKowalski11", "Qwerty111", Set.of(PersonType.CLIENT, PersonType.EMPLOYEE));
        clientMaciek.setClientNumber(2);
        clientMaciek.setEmployeeAddress(new Address("Akacjowa", 5, 15, "02-456", "Warszawa"));
        clientMaciek.setPesel("12345677891");
        clientMaciek.setEmployeeGender(EmployeeGender.MALE);
        clientMaciek.setMiddleName(null);
        clientMaciek.setWorkStartDate(LocalDate.of(1988, 10, 23));
        employeeJakub = new Person("Jakub", "Maciejewski", "4321567879", LocalDate.of(2016, 6, 24), "jakubMaciejewski@gmail.com", "jakubM123", "passwd123", Set.of(PersonType.EMPLOYEE));
        employeeJakub.setPesel("14531239871");
        employeeJakub.setEmployeeAddress(new Address("Hassa", 6, 15, "02-123", "Warszawa"));
        employeeJakub.setMiddleName("Michał");
        employeeJakub.setEmployeeGender(EmployeeGender.MALE);
        employeeJakub.setWorkStartDate(LocalDate.of(2019, 2, 5));
        clientAgata.addNewBookingToClient(booking1);
        clientAgata.addNewSubscriptionToClient(subscription1);
        clientMaciek.addNewBookingToClient(booking2);
        clientMaciek.addNewSubscriptionToClient(subscription2);
        clientMaciek.addNewSubscriptionToClient(subscription3);
        clientMaciek.addScreeningServiceToEmployee(cinemaScreening1);
        clientMaciek.addScreeningServiceToEmployee(cinemaScreening2);
        employeeJakub.addScreeningServiceToEmployee(cinemaScreening3);
        employeeJakub.addScreeningServiceToEmployee(cinemaScreening2);
    }

    private void saveActors(Session session) {
        session.persist(actorChrisEvans);
        session.persist(actorPeterSohn);
        session.persist(actorTomCruise);
        session.persist(actorMilesTeller);
        session.persist(actorChrisPratt);
        session.persist(actorLauraDern);
    }

    private void saveActorMovies(Session session) {
        session.persist(prattJurassicWorld);
        session.persist(dernJurrassicWorld);
        session.persist(cruiseTopGun);
        session.persist(tellerTopGun);
        session.persist(evansBuzzAstral);
        session.persist(sohnBuzzAstral);
    }

    private void saveMovies(Session session) {
        session.persist(movieJurassicWorldDominion);
        session.persist(movieTopGunMaverick);
        session.persist(movieBuzzAstral);
    }

    private void saveMoviesReviews(Session session) {
        session.persist(jurassicReview1);
        session.persist(jurassicReview2);
        session.persist(topGunReview1);
        session.persist(topGunReview2);
        session.persist(buzzAstralReview1);
        session.persist(buzzAstralReview2);
    }

    private void savePersons(Session session) {
        session.persist(clientMaciek);
        session.persist(clientAgata);
        session.persist(employeeJakub);
    }

    private void saveCinemaHall(Session session) {
        session.persist(cinemaHall1);
        session.persist(cinemaHall2);
        session.persist(cinemaHall3);
    }

    private void saveSeats(Session session) {
        for (int i = 0; i < seats.size(); i++) {
            session.persist(seats.get(i));
        }
    }

    private void saveBookings(Session session) {
        session.persist(booking1);
        session.persist(booking2);
    }

    private void saveTickets(Session session) {
        session.persist(ticket1);
        session.persist(ticket2);
        session.persist(ticket3);
        session.persist(ticket4);
        session.persist(ticket5);
    }

    private void saveCinemaScreenings(Session session) {
        session.persist(cinemaScreening1);
        session.persist(cinemaScreening2);
        session.persist(cinemaScreening3);
    }

    private void saveSubscriptions(Session session) {
        session.persist(subscription1);
        session.persist(subscription2);
        session.persist(subscription3);
    }

    public void saveMockDataToDatabase(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        saveActors(session);
        saveMovies(session);
        saveActorMovies(session);
        saveMoviesReviews(session);
        saveBookings(session);
        saveTickets(session);
        savePersons(session);
        saveCinemaHall(session);
        saveSeats(session);
        saveSubscriptions(session);
        saveCinemaScreenings(session);
        session.getTransaction().commit();
        session.close();
    }
}
