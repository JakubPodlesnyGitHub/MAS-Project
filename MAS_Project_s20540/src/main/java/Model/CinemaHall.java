package Model;

import Enums.CinemaHallType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "CinemaHall")
@Access(AccessType.FIELD)
public class CinemaHall {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idCinemaHall;

    @Column(name = "Cinema Hall Name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CallHallType", nullable = false)
    private CinemaHallType cinemaHallType;

    @Column(name = "Sound Equipment Model", nullable = false)
    private String soundEquipmentModel;
    @Column(name = "Projector Name", nullable = false)
    private String projector;
    @Column(name = "Screen Size (INCH)", nullable = false)
    private double screenSize;

    //connections
    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Seat> seatsInCinemaHall = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaHall",fetch = FetchType.EAGER)
    private List<CinemaScreening> cinemaScreenings = new ArrayList<>();

    public CinemaHall() {

    }

    public CinemaHall(String name, CinemaHallType cinemaHallType, String soundEquipmentModel, String projector, double screenSize) throws Exception {
        setName(name);
        setCinemaHallType(cinemaHallType);
        setSoundEquipmentModel(soundEquipmentModel);
        setProjector(projector);
        setScreenSize(screenSize);
    }

    public Long getIdCinemaHall() {
        return idCinemaHall;
    }

    public void setIdCinemaHall(Long idCinemaHall) {
        this.idCinemaHall = idCinemaHall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null)
            throw new Exception("Cinema Hall Name cannot be null. This filed is mandatory");
        this.name = name;
    }

    public CinemaHallType getCinemaHallType() {
        return cinemaHallType;
    }

    public void setCinemaHallType(CinemaHallType cinemaHallType) throws Exception {
        if (cinemaHallType == null)
            throw new Exception("Cinema Hall Type cannot be null. This filed is mandatory");
        this.cinemaHallType = cinemaHallType;
    }

    public String getSoundEquipmentModel() {
        return soundEquipmentModel;
    }

    public void setSoundEquipmentModel(String soundEquipmentModel) throws Exception {
        if (soundEquipmentModel == null)
            throw new Exception("Cinema Hall Sound Equipment cannot be null. This filed is mandatory");
        this.soundEquipmentModel = soundEquipmentModel;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) throws Exception {
        if (soundEquipmentModel == null)
            throw new Exception("Cinema Hall Projector cannot be null. This filed is mandatory");
        this.projector = projector;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    //connections

    public List<Seat> getSeatsInCinemaHall() {
        return seatsInCinemaHall;
    }

    public List<CinemaScreening> getCinemaScreenings() {
        return cinemaScreenings;
    }


    public void removeCinemaScreeningFromCinemaHall(CinemaScreening cinemaScreeningToRemove) {
        getCinemaScreenings().remove(cinemaScreeningToRemove);
    }

    public void addCinemaScreening(CinemaScreening cinemaScreeningToAdd) {
        if (!getCinemaScreenings().contains(cinemaScreeningToAdd)) {
            getCinemaScreenings().add(cinemaScreeningToAdd);
            cinemaScreeningToAdd.setCinemaHall(this);
        }
    }

    public void addSeats(Seat newSeat) {
        if (!getSeatsInCinemaHall().contains(newSeat)) {
            getSeatsInCinemaHall().add(newSeat);
            newSeat.setCinemaHall(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cinema Hall Name: ").append(getName()).append("\n");
        sb.append("Cinema Hall Type: ").append(getCinemaHallType()).append("\n");
        sb.append("Cinema Hall Sound Equipment: ").append(getSoundEquipmentModel()).append("\n");
        sb.append("Cinema Hall Projector: ").append(getProjector()).append("\n");
        sb.append("Cinema Hall Screen Size: ").append(getScreenSize()).append("\n");
        return sb.toString();
    }
}
