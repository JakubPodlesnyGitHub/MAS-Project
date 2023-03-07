package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "ActorMovie")
@Access(AccessType.FIELD)
public class ActorMovie {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idActorMovie;
    @Column(name = "Actor Role In Movie", nullable = false)
    private String roleName;
    @Column(name = "Movie Awards Number", nullable = false)
    private int movieAwardsNumber;

    //connections
    @ManyToOne
    @JoinColumn(name = "ActorID")
    private Actor actor;
    @ManyToOne
    @JoinColumn(name = "MovieID")
    private Movie movie;

    public ActorMovie() {

    }

    public ActorMovie(String roleName, int movieAwardsNumber, Actor actor, Movie movie) throws Exception {
        setRoleName(roleName);
        setMovieAwardsNumber(movieAwardsNumber);
        setMovie(movie);
        setActor(actor);
    }

    public Long getIdActorMovie() {
        return idActorMovie;
    }

    public void setIdActorMovie(Long idActorMovie) {
        this.idActorMovie = idActorMovie;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getMovieAwardsNumber() {
        return movieAwardsNumber;
    }

    public void setMovieAwardsNumber(int movieAwardsNumber) {
        this.movieAwardsNumber = movieAwardsNumber;
    }
    //connections

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    private void setMovie(Movie newMovie) {
        this.movie = newMovie;
        this.movie.addActorToMovie(this);
    }

    private void setActor(Actor newActor) {
        this.actor = newActor;
        this.actor.addMovieToActor(this);
    }
}
