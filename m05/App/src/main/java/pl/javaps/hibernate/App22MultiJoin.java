package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Category;
import pl.javaps.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Hello world!
 */
public class App22MultiJoin {
    private static Logger logger = LogManager.getLogger(App22MultiJoin.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> query = em.createQuery(
             " select c from Category c" +
                " left join fetch c.products p" +
                " left join fetch p.reviews " +
                " where c.id=:id",
                 Category.class);
        query.setParameter("id",1L);
        List<Category> resultList = query.getResultList();
        for (Category category : resultList) {
            logger.info(category);
            logger.info(category.getProducts());
            for (Product product : category.getProducts()) {
                logger.info(product);
            }
        }
        em.getTransaction().commit();
        em.close();

    }

}
