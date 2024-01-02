package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.Order;
import pl.javaps.hibernate.entity.Product;
import pl.javaps.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App43SortingAndOrdering {
    private static Logger logger = LogManager.getLogger(App43SortingAndOrdering.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);

        Product product = em.find(Product.class, 1L);
//
//        Review review = new Review();
//        review.setContent("Content 1");
//        review.setRating(10);
//        review.setProduct(product);
//
//        Review review2 = new Review();
//        review2.setContent("Content 2");
//        review2.setRating(10);
//        review2.setProduct(product);
//
//        Review review3 = new Review();
//        review3.setContent("Content 3");
//        review3.setRating(10);
//        review3.setProduct(product);
//
//        customer.getReviews().add(review);
//        customer.getReviews().add(review2);
//        customer.getReviews().add(review3);

        Customer customer1 = em.createQuery(
                        "select c from Customer c" +
                                " inner join fetch c.reviews r" +
                                " where c.id =:id" +
                        " order by r.id desc",
                        Customer.class
                )
                .setParameter("id", 1L)
                .getSingleResult();

        customer1.getReviews().forEach(review -> {
            logger.info(review);
        });

        em.getTransaction().commit();
        em.close();

    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
