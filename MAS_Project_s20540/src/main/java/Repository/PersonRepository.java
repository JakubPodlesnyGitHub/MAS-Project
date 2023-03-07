package Repository;

import Model.Person;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonRepository {

    private SessionFactory sessionFactory;

    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int changePassword(Person person, String newPassword) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String updatePasswordSQL = "UPDATE Person SET password = :newPassword WHERE idPerson = :personId";
        int result = session.createQuery(updatePasswordSQL, Person.class)
                .setParameter("newPassword", newPassword)
                .setParameter("personId", person.getIdPerson())
                .executeUpdate();
        System.out.println("Rows Affected: " + result);
        session.getTransaction().commit();
        session.close();
        person.setPassword(newPassword);
        return result;
    }

    public Person getPersonByLoginPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String loginSQL = "FROM Person WHERE login = :login AND password = :password";
        Person person = null;
        try {
            person = session.createQuery(loginSQL, Person.class).
                    setParameter("login", login).
                    setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.getTransaction().commit();
        session.close();
        return person;
    }

    public void createNewPerson(Person newPerson) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newPerson);
        session.getTransaction().commit();
        session.close();
    }

    public List<Person> getPersonsData() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getPersonsSQL = "FROM Person";
        List<Person> persons = null;
        try {
            persons = session.createQuery(getPersonsSQL, Person.class).list();
        } catch (NoResultException ignored) {
        }
        session.getTransaction().commit();
        session.close();
        return persons;
    }

    public String getPersonPassword(String login) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String getPersonPasswordSQL = "SELECT password from Person where login = :personLogin";
        String personPassword = null;
        try {
            personPassword = session.createQuery(getPersonPasswordSQL, String.class)
                    .setParameter("personLogin", login)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        }
        session.getTransaction().commit();
        session.close();
        return personPassword;
    }
}
