package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Order;
import pl.javaps.hibernate.entity.OrderRow;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App29NplusOne {
    private static Logger logger = LogManager.getLogger(App29NplusOne.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        List<Order> products = em.createQuery(
//                        "select distinct o from Order o" +
//                           " inner join fetch o.orderRows", Order.class
//                )
//                .getResultList();
//
//        logger.info("Ilość zamówień: " + products.size());
//        for (Order order : products) {
//            logger.info(order.getId());
//            logger.info(order.getOrderRows());
//        }

//        EntityGraph entityGraph = em.getEntityGraph("order-and-rows");
//
//        List<Order> products = em.createQuery(
//                        "select o from Order o", Order.class
//                )
//                .setHint("javax.persistence.fetchgraph", entityGraph)
//                .getResultList();
//
//        logger.info("Ilość zamówień: " + products.size());
//        for (Order order : products) {
//            logger.info(order.getId());
//            logger.info(order.getOrderRows());
//        }



        List<Order> products = em.createQuery(
                        "select o from Order o", Order.class
                )
                .getResultList();

        logger.info("Ilość zamówień: " + products.size());
        for (Order order : products) {
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();

    }

}
