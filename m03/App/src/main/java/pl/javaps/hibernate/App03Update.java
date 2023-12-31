package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App03Update
{

    private static Logger logger = LogManager.getLogger(App03Update.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        logger.info("hello!");

//        Product product = em.find(Product.class, 1L);
//        product.setName("Nowy rower");
        //Product merged = em.merge(product);

        Product product = new Product();
        product.setId(1L);
        product.setName("Nowy rower03");

        Product merged = em.merge(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();
    }
}
