package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Attribute;
import pl.javaps.hibernate.entity.Product;
import pl.javaps.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App13AddOneToMany
{
    private static Logger logger = LogManager.getLogger(App13AddOneToMany.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
//        Review review = new Review();
//        review.setContent("Noa opinia 12");
//        review.setRating(5);
//        review.setProduct(product);
        Review review = em.find(Review.class, 12L);

        product.addReview(review);


        logger.info(product);

        em.getTransaction().commit();
        em.close();

    }
}
