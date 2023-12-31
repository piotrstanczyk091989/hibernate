package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Attribute;
import pl.javaps.hibernate.entity.Category;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App12AddManyToMany
{
    private static Logger logger = LogManager.getLogger(App12AddManyToMany.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
//        Attribute attribute = em.find(Attribute.class, 1L);
//        product.addAttribute(attribute);
        Attribute attribute = new Attribute();
        attribute.setName("COLOR");
        attribute.setValue("YELLOW");
        product.addAttribute(attribute);

        logger.info(product);

        em.getTransaction().commit();
        em.close();

    }
}
