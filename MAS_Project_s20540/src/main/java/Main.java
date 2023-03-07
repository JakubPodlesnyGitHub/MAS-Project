import GUI.Login.Login;
import MockData.MockDataFulfillment;
import Repository.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            MockDataFulfillment mockDataFulfillment = new MockDataFulfillment();
            mockDataFulfillment.saveMockDataToDatabase(sessionFactory);
            RepositoryManager repositoryManager = new RepositoryManager(sessionFactory);
            SwingUtilities.invokeLater(() -> {
                Login login = new Login(repositoryManager);
            });

            //MKowalski11
            //
            //Qwerty111

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            /*if (sessionFactory != null) {
                sessionFactory.close();
            }*/
        }
    }
}
