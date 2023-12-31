package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App32UpdateScrolling {
    private static Logger logger = LogManager.getLogger(App32UpdateScrolling.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery(
                        "select r from BatchReview r",
                        BatchReview.class)
                .setHint(QueryHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream()
                .forEach(batchReview -> {
                    batchReview.setContent("Content");
                    batchReview.setRating(5);
                    logger.info(batchReview);
                });


        em.getTransaction().commit();
        em.close();

    }

}
