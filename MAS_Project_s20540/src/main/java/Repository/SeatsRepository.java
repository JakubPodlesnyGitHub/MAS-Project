package Repository;

import Model.Seat;
import Other.CurrentData;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeatsRepository {
    SessionFactory sessionFactory;

    public SeatsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Object[][] getDataToTable() {
        List<Seat> seats = CurrentData.cinemaScreening.getSeats();
        Object[][] seatsInfo = new Object[CurrentData.cinemaScreening.getSeats().size()][6];
        for (int i = 0; i < seats.size(); i++) {
            seatsInfo[i] = new Object[]{seats.get(i).getSeatNumber(), seats.get(i).getRow(), seats.get(i).getCinemaSeatType(), seats.get(i).getCinemaSeatType().getDescription(), seats.get(i).getCinemaSeatType().getAdditionalCost(), false};
        }
        return seatsInfo;
    }

}
