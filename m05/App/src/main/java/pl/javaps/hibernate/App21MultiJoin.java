package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Hello world!
 */
public class App21MultiJoin {
    private static Logger logger = LogManager.getLogger(App21MultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery(
             "select p from Product p " +
                "left join fetch p.category c" , Product.class);
        //query.setParameter("id",1L);
        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product);
            if(product.getCategory() != null){
                logger.info(product.getCategory().getName());
            }else {
                logger.info(product.getCategory());
            }
        }

        em.getTransaction().commit();
        em.close();

    }

}
