package Model;

import RequirementsInfo.StaticProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.GenericGenerator;

import java.util.Random;

@Entity(name = "MovieReview")
@Access(AccessType.FIELD)
public class MovieReview {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idMovieReview;
    @Column(name = "Movie Review Code", nullable = false, unique = true)
    private int reviewCode;

    @Column(name = "Movie Review Description", nullable = false, length = 500)
    private String description;
    @Min(1)
    @Max(10)
    @Column(name = "Movie Review Rate", nullable = false)
    private int reviewRate;

    //connections
    @ManyToOne
    @JoinColumn(name = "MovieID")
    private Movie movie;

    public MovieReview() {

    }

    public MovieReview(String description, int reviewRate) throws Exception {
        setReviewCode(new Random().nextInt(10000));
        setDescription(description);
        setReviewRate(reviewRate);
    }

    public Long getIdMovieReview() {
        return idMovieReview;
    }

    public void setIdMovieReview(Long idMovieReview) {
        this.idMovieReview = idMovieReview;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        if (description == null)
            throw new Exception("Movie Review Description cannot be null. This filed is mandatory");
        if (description.length() > StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_DESCRIPTION_VALUE)
            throw new Exception("Movie Review Description is too long -> " + description.length() + "Max Length is: " + StaticProperties.getStaticPropertiesInstance().MAX_LENGTH_DESCRIPTION_VALUE);
        this.description = description;
    }

    public int getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(int reviewRate) throws Exception {
        if (reviewRate < StaticProperties.getStaticPropertiesInstance().MIN_RATE_VALUE || reviewRate > StaticProperties.getStaticPropertiesInstance().MAX_RATE_VALUE)
            throw new Exception("The average rating of the review should be in between 1 and 10. You entered: " + reviewRate);
        this.reviewRate = reviewRate;
    }

    //connections
    public void setMovie(Movie newMovie) {
        if (newMovie.equals(this.movie))
            return;
        if (this.movie != null)
            this.movie.removeReviewFromMovie(this);
        this.movie = newMovie;
        newMovie.addReviewToMovie(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie Review Code: ").append(getReviewCode()).append("\n");
        sb.append("Movie Description: ").append(getDescription()).append("\n");
        sb.append("Movie Review Rate: ").append(getReviewRate()).append("\n");
        return sb.toString();
    }
}
