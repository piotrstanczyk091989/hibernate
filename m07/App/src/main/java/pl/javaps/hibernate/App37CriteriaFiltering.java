package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Hello world!
 */
public class App37CriteriaFiltering {
    private static Logger logger = LogManager.getLogger(App37CriteriaFiltering.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customer.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows")
                .fetch("product");
//        ParameterExpression<Long> id1 = criteriaBuilder.parameter(Long.class);
//        ParameterExpression<Long> id2 = criteriaBuilder.parameter(Long.class);
        ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
        ParameterExpression<String> lastname = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(customer).distinct(true)
                // where c.id=:id and order.total > 50
                .where(
                        criteriaBuilder.and(
                                customer.get("id").in(ids),
                                //criteriaBuilder.or(
                                       // criteriaBuilder.like(customer.get("lastname"), criteriaBuilder.concat(lastname, "%"))
//                                        criteriaBuilder.equal(customer.get("id"), id1),
//                                        criteriaBuilder.equal(customer.get("id"), id2)
                              //          ),
                                criteriaBuilder.between(orders.get("total"), total, criteriaBuilder.literal(
                                        new BigDecimal("70.00")
                                )),
                                criteriaBuilder.isNotNull(customer.get("firstname"))
                        )
                );

        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
//        query.setParameter(id1, 1L);
//        query.setParameter(id2, 2L);
        query.setParameter(ids, Arrays.asList(1L,2L,4L));
        //query.setParameter(lastname, "Kow");
        query.setParameter(total, new BigDecimal("30.00"));
        List<Customer> results = query.getResultList();

        for (Customer result : results) {
            logger.info(result);
            logger.info(result.getOrders());
        }

        em.getTransaction().commit();
        em.close();

    }

}
