package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App27QueryWithIn {
    private static Logger logger = LogManager.getLogger(App27QueryWithIn.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Order> orders = em.createQuery("select o from Order o" +
                        " where id NOT in (:ids)", Order.class)
                .setParameter("ids", Arrays.asList(1L,3L,5L))
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
        }


        em.getTransaction().commit();
        em.close();

    }

}
