package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 */
public class App49QueryCache {
    private static Logger logger = LogManager.getLogger(App49QueryCache.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Customer> customers = em.createQuery(" select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .getResultList();
        logger.info(customers);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customers = em.createQuery(" select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE,true)
                .getResultList();
        logger.info(customers);
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