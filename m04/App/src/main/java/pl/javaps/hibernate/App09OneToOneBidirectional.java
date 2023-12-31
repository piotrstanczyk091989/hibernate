package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Category;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App09OneToOneBidirectional
{
    private static Logger logger = LogManager.getLogger(App09OneToOneBidirectional.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Category category = em.find(Category.class, 1L);
        logger.info(category);
        logger.info(category.getProducts());

        em.getTransaction().commit();
        em.close();

    }
}
