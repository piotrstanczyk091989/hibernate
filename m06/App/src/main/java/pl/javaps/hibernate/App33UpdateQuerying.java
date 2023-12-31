package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.javaps.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App33UpdateQuerying {
    private static Logger logger = LogManager.getLogger(App33UpdateQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        int updated = em.createQuery("update Review r SET rating=:rating" +
                " where r.product.id=:id")
                .setParameter("rating", 11)
                .setParameter("id", 1L)
                .executeUpdate();
        logger.info("zaktualizowano " + updated);

        em.getTransaction().commit();
        em.close();

    }

}
