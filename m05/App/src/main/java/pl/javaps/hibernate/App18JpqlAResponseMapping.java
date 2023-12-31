package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.ProductInCategoryCounterDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App18JpqlAResponseMapping {
    private static Logger logger = LogManager.getLogger(App18JpqlAResponseMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select p.category.id, COUNT(p) from Product p group by p.category");

        List<Object[]> resultList = query.getResultList();
        List<ProductInCategoryCounterDto> result = resultList.stream()
                .map(objects -> new ProductInCategoryCounterDto((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());

            for (ProductInCategoryCounterDto dto : result) {
                logger.info(dto.getCategoryId());
                logger.info(dto.getProductInCategoryCounter());
            }


        em.getTransaction().commit();
        em.close();

    }

}
