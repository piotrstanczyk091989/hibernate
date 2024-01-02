package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

/**
 * Hello world!
 */
public class App50PesimisticLocking {
    private static Logger logger = LogManager.getLogger(App49OptimisticLocking.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Order order = em.find(Order.class, 3L, LockModeType.PESSIMISTIC_READ);
//        order.setTotal(new BigDecimal("22.55"));
//        logger.info(order);

        Order order = em.createQuery("select o from Order o where o.id=:id", Order.class)
                .setParameter("id", 3L)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        logger.info(order);

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
