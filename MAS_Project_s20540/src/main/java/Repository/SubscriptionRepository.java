package Repository;

import Model.Subscription;
import RequirementsInfo.StaticProperties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionRepository {
    private SessionFactory sessionFactory;

    public SubscriptionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addSubscription(Subscription newSubscription) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newSubscription);
        session.getTransaction().commit();
        session.close();
    }

    public List<Subscription> getSubscriptionBasedOnStartDate(LocalDate startDate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getSubscriptionBasedOnPeriodSQL = "FROM Subscription WHERE subscriptionStartDate >= :startDate";
        List<Subscription> subscriptions = null;
        try {
            subscriptions = session.createQuery(getSubscriptionBasedOnPeriodSQL, Subscription.class).
                    setParameter("startDate", startDate).list();
        } catch (Exception ignored) {}
        session.getTransaction().commit();
        session.close();
        return subscriptions;
    }

    public int updateSubscriptionTime(int newSubscriptionTime) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String updateSubscriptionTimeSQL = "UPDATE StaticProperties SET SUBSCRIPTION_TIME = :newTime";
        int result = session.createQuery(updateSubscriptionTimeSQL, Subscription.class)
                .setParameter("newTime", newSubscriptionTime)
                .executeUpdate();
        System.out.println("Rows Affected: " + result);
        session.getTransaction().commit();
        session.close();
        StaticProperties.getStaticPropertiesInstance().SUBSCRIPTION_TIME = newSubscriptionTime;
        return result;
    }

    public int updateSubscriptionPrice(int newSubscriptionPrice) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String updateSubscriptionTimeSQL = "UPDATE StaticProperties SET SUBSCRIPTION_PRICE_PLN = :newPrice";
        int result = session.createQuery(updateSubscriptionTimeSQL, Subscription.class)
                .setParameter("newPrice", newSubscriptionPrice)
                .executeUpdate();
        System.out.println("Rows Affected: " + result);
        session.getTransaction().commit();
        session.close();
        StaticProperties.getStaticPropertiesInstance().SUBSCRIPTION_TIME = newSubscriptionPrice;
        return result;
    }
}
