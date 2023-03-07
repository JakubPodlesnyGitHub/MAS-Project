package Model;

import Enums.MovieAgeCategory;
import RequirementsInfo.StaticProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movie")
@Access(AccessType.FIELD)
public class Movie {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idMovie;
    @Column(name = "Movie Title", nullable = false)
    private String title;
    @Column(name = "Movie Genre", nullable = false)
    private String movieGenre;

    @Column(name = "Movie Director", nullable = false)
    private String director;
    @Min(1)
    @Max(10)
    @Column(name = "Movie Averge Rate", nullable = false)
    private int averageRate;
    @Column(name = "Movie Duration", nullable = false)
    private double movieDuration;
    @Column(name = "Movie Release Date", nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Movie Age Category", nullable = false)
    private MovieAgeCategory movieAgeCategory;

    //connections
    @OneToMany(mappedBy = "movie")
    private List<ActorMovie> actorsInMovie = new ArrayList<>();
    @OneToMany(mappedBy = "movie")
    private List<MovieReview> movieReviews = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<CinemaScreening> cinemaScreenings = new ArrayList<>();

    public Movie() {

    }

    public Movie(String title, String movieGenre, String director, int averageRate, double movieDuration, LocalDate releaseDate, MovieAgeCategory movieAgeCategory) throws Exception {
        setTitle(title);
        setMovieGenre(movieGenre);
        setDirector(director);
        setAverageRate(averageRate);
        setMovieDuration(movieDuration);
        setReleaseDate(releaseDate);
        setMovieAgeCategory(movieAgeCategory);
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if (title == null)
            throw new Exception("Movie Title cannot be null. This filed is mandatory");
        this.title = title;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) throws Exception {
        if (movieGenre == null)
            throw new Exception("Movie Genre cannot be null. This filed is mandatory");
        this.movieGenre = movieGenre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) throws Exception {
        if (director == null)
            throw new Exception("Director cannot be null. This filed is mandatory");
        this.director = director;
    }

    public int getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(int averageRate) throws Exception {
        if (averageRate < StaticProperties.getStaticPropertiesInstance().MIN_RATE_VALUE || averageRate > StaticProperties.getStaticPropertiesInstance().MAX_RATE_VALUE)
            throw new Exception("The average rating of the movie should be in between 1 and 10. You entered: " + averageRate);
        this.averageRate = averageRate;
    }

    public double getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(double movieDuration) {
        this.movieDuration = movieDuration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) throws Exception {
        if (releaseDate == null)
            throw new Exception("Movie Release Date cannot be null. This filed is mandatory");
        this.releaseDate = releaseDate;
    }

    public MovieAgeCategory getMovieAgeCategory() {
        return movieAgeCategory;
    }

    public void setMovieAgeCategory(MovieAgeCategory movieAgeCategory) throws Exception {
        if (movieAgeCategory == null)
            throw new Exception("Movie Age Category cannot be null. This filed is mandatory");
        this.movieAgeCategory = movieAgeCategory;
    }

    //connections
    public List<ActorMovie> getActorsInMovie() {
        return actorsInMovie;
    }

    public void setActorsInMovie(List<ActorMovie> actorsInMovie) {
        this.actorsInMovie = actorsInMovie;
    }

    public List<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    public List<CinemaScreening> getCinemaScreenings() {
        return cinemaScreenings;
    }

    public void setCinemaScreenings(List<CinemaScreening> cinemaScreenings) {
        this.cinemaScreenings = cinemaScreenings;
    }

    public void addActorToMovie(ActorMovie newActorRole) {
        if (!getActorsInMovie().contains(newActorRole)) {
            getActorsInMovie().add(newActorRole);
        }
    }

    public void removeActorFromMovie(ActorMovie actorToRemove) {
        if (getActorsInMovie().contains(actorToRemove)) {
            getActorsInMovie().remove(actorToRemove);
        }
    }

    public void addReviewToMovie(MovieReview newMovieReview) {
        if (!getMovieReviews().contains(newMovieReview)) {
            getMovieReviews().add(newMovieReview);
            newMovieReview.setMovie(this);
        }
    }

    public void removeReviewFromMovie(MovieReview movieReviewToDelete) {
        if (getMovieReviews().contains(movieReviewToDelete)) {
            getMovieReviews().remove(movieReviewToDelete);
            movieReviewToDelete.setMovie(null);
        }
    }

    public void addCinemaScreening(CinemaScreening newCinemaScreening) {
        if (!getCinemaScreenings().contains(newCinemaScreening)) {
            getCinemaScreenings().add(newCinemaScreening);
            newCinemaScreening.setMovie(this);
        }
    }

    public void removeCinemaScreening(CinemaScreening cinemaScreeningToRemove) {
        if (getCinemaScreenings().contains(cinemaScreeningToRemove)) {
            getCinemaScreenings().remove(cinemaScreeningToRemove);
            cinemaScreeningToRemove.setMovie(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie Title: ").append(getTitle()).append("\n");
        sb.append("Movie Genre: ").append(getMovieGenre()).append("\n");
        sb.append("Movie Director: ").append(getDirector()).append("\n");
        sb.append("Movie Average Rate: ").append(getAverageRate()).append("\n");
        sb.append("Movie Duration: ").append(getReleaseDate()).append("\n");
        sb.append("Movie Age Category: ").append(getMovieAgeCategory()).append("\n");
        return sb.toString();
    }
}
