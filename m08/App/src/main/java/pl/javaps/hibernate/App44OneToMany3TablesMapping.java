package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Note;
import pl.javaps.hibernate.entity.Order;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * Hello world!
 */
public class App44OneToMany3TablesMapping {
    private static Logger logger = LogManager.getLogger(App44OneToMany3TablesMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);

        customer.getNotes().add(new Note("Content 1", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 2", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 3", LocalDateTime.now()));
        customer.getNotes().add(new Note("Content 4", LocalDateTime.now()));

        em.getTransaction().commit();
        em.close();

    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
