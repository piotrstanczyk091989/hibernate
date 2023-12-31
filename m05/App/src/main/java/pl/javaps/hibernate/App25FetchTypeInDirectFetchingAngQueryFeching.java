package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Hello world!
 */
public class App25FetchTypeInDirectFetchingAngQueryFeching {
    private static Logger logger = LogManager.getLogger(App25FetchTypeInDirectFetchingAngQueryFeching.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.createQuery("select p from Product p" +
                        " left join fetch p.category c" +
                        " where p.id=:id and c.id=:catId", Product.class)
                .setParameter("id", 1L)
                .setParameter("catId", 1L)
                .getSingleResult();

//        Product product = em.find(Product.class, 1L);

        logger.info(product);
        logger.info(product.getCategory());
        logger.info(product.getReviews());

        em.getTransaction().commit();
        em.close();

    }

}
