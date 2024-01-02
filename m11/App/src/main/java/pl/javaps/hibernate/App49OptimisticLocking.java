package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

/**
 * Hello world!
 */
public class App49OptimisticLocking {
    private static Logger logger = LogManager.getLogger(App49OptimisticLocking.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();

        Order order = em.find(Order.class, 3L);
        Order order2 = em2.find(Order.class, 3L);

        order.setTotal(new BigDecimal("22.55"));

        logger.info(order);

        em.getTransaction().commit();
        em.close();

        order2.setTotal(new BigDecimal("22.66"));

        logger.info(order2);

        em2.getTransaction().commit();
        em2.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
