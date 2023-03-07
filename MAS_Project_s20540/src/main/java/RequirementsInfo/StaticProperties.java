package RequirementsInfo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.time.format.DateTimeFormatter;

@Entity(name = "StaticProperties")
@Access(AccessType.FIELD)
public class StaticProperties {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @ColumnDefault("1")
    private Long idStaticProperty;
    @ColumnDefault("20")
    @Column(name = "Points For Movie", nullable = false)
    public int POINTS_FOR_MOVIE = 20;
    @ColumnDefault("30")
    @Column(name = "Subscription Time", nullable = false)
    public int SUBSCRIPTION_TIME = 30;
    @ColumnDefault("200")
    @Column(name = "Subscription PRICE (PLN)", nullable = false)
    public int SUBSCRIPTION_PRICE_PLN = 200;
    @ColumnDefault("300")
    @Column(name = "Subscription Price MembershipPoints", nullable = false)
    public int SUBSCRIPTION_PRICE_MEMBERSHIPPOINTS = 300;
    @ColumnDefault("'^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{10,}$'")
    @Column(name = "Password Pattern", nullable = false)
    public String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{10,}$";
    @ColumnDefault("'^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$'")
    @Column(name = "Phone Number Pattern", nullable = false)
    public String PHONE_NUMBER_PATTERN = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    @ColumnDefault("'^\\d{11}$'")
    @Column(name = "Pesel Pattern", nullable = false)
    public String PESEL_PATTERN = "^\\d{11}$";
    @ColumnDefault("'^\\d{2}[\\-]\\d{3}$'")
    @Column(name = "Zip Code Pattern", nullable = false)
    public String ZIP_CODE_PATTERN = "^\\d{2}[\\-]\\d{3}$";
    @ColumnDefault("100")
    @Column(name = "Max Length Login", nullable = false)
    public int MAX_LENGTH_LOGIN = 100;
    @ColumnDefault("1")
    @Column(name = "Min Rate Value", nullable = false)
    public int MIN_RATE_VALUE = 1;
    @ColumnDefault("10")
    @Column(name = "Max Rate Value", nullable = false)
    public int MAX_RATE_VALUE = 10;
    @ColumnDefault("500")
    @Column(name = "Max Length Description Value", nullable = false)
    public int MAX_LENGTH_DESCRIPTION_VALUE = 500;

    @Transient
    public DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static StaticProperties instance;

    public StaticProperties() {
    }

    public void setIdStaticProperty(Long idStaticProperty) {
        this.idStaticProperty = idStaticProperty;
    }

    public Long getIdStaticProperty() {
        return idStaticProperty;
    }

    public static StaticProperties getStaticPropertiesInstance() {
        if (instance == null)
            instance = new StaticProperties();
        return instance;
    }
}
