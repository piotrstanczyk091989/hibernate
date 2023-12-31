package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Attribute;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App15DeleteManyToMany
{
    private static Logger logger = LogManager.getLogger(App15DeleteManyToMany.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Product product = em.find(Product.class, 5L);
//        product.getAttributes().clear();
//
        Attribute attribute = em.find(Attribute.class, 1L);
        for (Product product : new ArrayList<>(attribute.getProducts())) {
            attribute.removeProduct(product);
        }
        em.remove(attribute);


//        logger.info(product);

        em.getTransaction().commit();
        em.close();

    }
}
