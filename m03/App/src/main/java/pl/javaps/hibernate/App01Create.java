package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Product;
import pl.javaps.hibernate.entity.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App01Create
{

    private static Logger logger = LogManager.getLogger(App01Create.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        logger.info("hello!");

        Product product = new Product();
        product.setName("Rower 01");
        product.setDescription("Opis produktu 01");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("19.99"));
        product.setProductType(ProductType.REAL);

        em.persist(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();
    }
}
