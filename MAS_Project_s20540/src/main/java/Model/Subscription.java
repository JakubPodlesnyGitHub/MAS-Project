package Model;

import Enums.PersonType;
import RequirementsInfo.StaticProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idSubscription;

    @Column(name = "Subscription Code", nullable = false, unique = true)
    private String subscriptionId;
    @Column(name = "Subscription Start Date", nullable = false)
    private LocalDate subscriptionStartDate;

    @ElementCollection
    @Column(name = "Discounts", nullable = false)
    private static List<Double> discounts = List.of(0.1, 0.2, 0.3, 0.4, 0.5);

    //connections
    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Person client;

    public Subscription() {

    }

    public Subscription(String subscriptionId, LocalDate subscriptionStartDate) throws Exception {
        setSubscriptionId(subscriptionId);
        setSubscriptionStartDate(subscriptionStartDate);
    }

    public void setIdSubscription(Long idSubscription) {
        this.idSubscription = idSubscription;
    }

    public Long getIdSubscription() {
        return idSubscription;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) throws Exception {
        if (subscriptionId == null)
            throw new Exception("Subscription Id cannot be null. This filed is mandatory");
        this.subscriptionId = subscriptionId;
    }

    public LocalDate getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(LocalDate subscriptionStartDate) throws Exception {
        if (subscriptionStartDate == null)
            throw new Exception("Subscription Start Date cannot be null. This filed is mandatory");
        this.subscriptionStartDate = LocalDate.parse(StaticProperties.getStaticPropertiesInstance().DATE_FORMAT.format(subscriptionStartDate));
    }

    public static List<Double> getDiscounts() {
        return discounts;
    }

    //wyliczalne
    @Transient
    public LocalDate getSubscriptionEndDate() {
        return subscriptionStartDate.plusDays(StaticProperties.getStaticPropertiesInstance().SUBSCRIPTION_TIME);
    }

    //connections
    public void setClient(Person newClient) throws Exception {
        if (!newClient.checkPersonType(PersonType.CLIENT))
            throw new Exception("You do not have access to method: setClient. Only client has access.");

        if (newClient.equals(this.client))
            return;
        if (this.client != null)
            this.client.removeSubscriptionFromClient(this);
        this.client = newClient;
        newClient.addNewSubscriptionToClient(this);
    }

    //use case methods
    public LocalDate checkSubscriptionEndDate() {
        return getSubscriptionEndDate();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Subscription Id: ").append(getSubscriptionId()).append("\n");
        sb.append("Subscription Start Date: ").append(getSubscriptionStartDate()).append("\n");
        sb.append("Subscription End Date: ").append(getSubscriptionEndDate());
        sb.append("Subscription Available Cinema Bar Discounts: ").append(String.join("-", discounts.toString()));
        return sb.toString();
    }
}
