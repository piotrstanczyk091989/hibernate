package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 */
public class App34Delete {
    private static Logger logger = LogManager.getLogger(App34Delete.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<BatchReview> review = em.createQuery(
                        "select r from BatchReview r" +
                                " where r.productId=:id",
                        BatchReview.class
                )
                .setParameter("id", 1L)
                .getResultList();

        for (BatchReview batchReview : review) {
            logger.info(batchReview);
            em.remove(batchReview);
        }

        em.getTransaction().commit();
        em.close();

    }

}
