package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.QueryHints;
import pl.javaps.hibernate.entity.Category;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

/**
 * Hello world!
 */
public class App23cartesianProduct {
    private static Logger logger = LogManager.getLogger(App23cartesianProduct.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Product> resultList = em.createQuery(
                "select distinct p from Product p" +
                   " left join fetch p.attributes"
                , Product.class
        ).setHint(QueryHints.PASS_DISTINCT_THROUGH,false)
                .getResultList();

        resultList = em.createQuery(
                "select distinct p from Product p" +
                        " left join fetch p.reviews"
                , Product.class
        ).setHint(QueryHints.PASS_DISTINCT_THROUGH,false)
                .getResultList();

        logger.info("Size - " + resultList.size());
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }


        em.getTransaction().commit();
        em.close();

    }

}
