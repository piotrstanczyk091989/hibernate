package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Product;
import pl.javaps.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App06ManyToOneBidirectional
{
    private static Logger logger = LogManager.getLogger(App06ManyToOneBidirectional.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Review> reviews = em.createQuery("select r from Review r").getResultList();
        for (Review review : reviews){
            logger.info(review);
            logger.info(review.getProduct());
        }

        em.getTransaction().commit();
        em.close();

    }
}
