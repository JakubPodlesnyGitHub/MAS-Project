package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Actor")
@Access(AccessType.FIELD)
public class Actor {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idActor;
    @Column(name = "Actor First Name", nullable = false)
    private String firstName;
    @Column(name = "Actor Last Name", nullable = false)
    private String lastName;
    @Column(name = "Actor Birth Date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "Actor Oscar", nullable = true)
    private Boolean hasOscar;
    @Column(name = "Actor Acting School", nullable = false)
    private String actingSchool;

    //connections
    @OneToMany(mappedBy = "actor")
    List<ActorMovie> actorMovies = new ArrayList<>();

    public Actor() {

    }

    public Actor(String firstName, String lastName, LocalDate birthDate, boolean hasOscar, String actingSchool) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setHasOscar(hasOscar);
        setActingSchool(actingSchool);
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName == null)
            throw new Exception("Actor FirstName cannot be null. This filed is mandatory");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName == null)
            throw new Exception("Actor LastName cannot be null. This filed is mandatory");
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if (birthDate == null)
            throw new Exception("Actor BirthDate cannot be null. This filed is mandatory");
        this.birthDate = birthDate;
    }

    public Boolean getHasOscar() {
        return hasOscar;
    }

    public void setHasOscar(Boolean hasOscar) {
        this.hasOscar = hasOscar;
    }

    public String getActingSchool() {
        return actingSchool;
    }

    public void setActingSchool(String actingSchool) throws Exception {
        if (actingSchool == null)
            throw new Exception("Actor ActingSchool cannot be null. This filed is mandatory");
        this.actingSchool = actingSchool;
    }

    //connections
    public List<ActorMovie> getActorMovies() {
        return actorMovies;
    }

    public void setActorMovies(List<ActorMovie> actorMovies) {
        this.actorMovies = actorMovies;
    }

    public void addMovieToActor(ActorMovie newMovieRole) {
        if (!getActorMovies().contains(newMovieRole)) {
            getActorMovies().add(newMovieRole);
        }
    }

    public void removeActorFromMovie(ActorMovie actorToRemove) {
        if (getActorMovies().contains(actorToRemove)) {
            getActorMovies().remove(actorToRemove);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actor First Name: ").append(firstName);
        sb.append("Actor Last Name: ").append(lastName);
        sb.append("Actor Birth Date: ").append(lastName);
        sb.append("If Actor Has Oscar: ").append((hasOscar) ? "YES" : "NO");
        sb.append("Actor Acting School: ").append(actingSchool).append("\n");
        return sb.toString();
    }
}
