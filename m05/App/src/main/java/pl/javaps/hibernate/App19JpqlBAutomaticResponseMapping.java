package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.ProductInCategoryCounterDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Hello world!
 */
public class App19JpqlBAutomaticResponseMapping {
    private static Logger logger = LogManager.getLogger(App19JpqlBAutomaticResponseMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select new pl.javaps.hibernate.entity.ProductInCategoryCounterDto(p.category.id, COUNT(p)) " +
                        "from Product p group by p.category"
                );
//        query.setParameter("id", 31L);

        List<ProductInCategoryCounterDto> resultList = query.getResultList();
        for (ProductInCategoryCounterDto dto : resultList) {
            logger.info(dto.getCategoryId());
            logger.info(dto.getProductInCategoryCounter());
        }


        em.getTransaction().commit();
        em.close();

    }

}
