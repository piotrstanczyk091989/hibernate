package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Order;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.time.LocalDateTime;

/**
 * Hello world!
 */
public class App46ReadOnly {
    private static Logger logger = LogManager.getLogger(App46ReadOnly.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


            Customer customer = em.createQuery(" select c from Customer c where c.id=:id", Customer.class)
                    .setParameter("id", 1L)
                    .setHint(QueryHints.HINT_READONLY, true)
                    .getSingleResult();
            logger.info(customer);

        customer.setUpdated(LocalDateTime.now());
        em.merge(customer);

        em.flush();
        em.clear();

        Customer customer1 = em.createQuery(" select c from Customer c where c.id=:id", Customer.class)
                .setParameter("id", 1L)
                .setHint(QueryHints.HINT_READONLY, true)
                .getSingleResult();
        logger.info(customer1);
        logger.info(customer);

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
