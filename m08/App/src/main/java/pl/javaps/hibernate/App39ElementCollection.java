package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Address;
import pl.javaps.hibernate.entity.AddressType;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Hello world!
 */
public class App39ElementCollection {
    private static Logger logger = LogManager.getLogger(App39ElementCollection.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer();
        customer.setFirstname("Customer 1");
        customer.setLastname("Customer 1");
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        customer.setAddress(Arrays.asList(
                new Address(AddressType.BILLING, "Polna 10/10", "00-000","Warszawa"),
                new Address(AddressType.SHIPPING, "Letnia 10/10", "00-000","Warszawa"),
                new Address(AddressType.INVOICE, "Wiosena 10/10", "00-000","Warszawa")

        ));

        em.persist(customer);

        em.flush();
        em.clear();

        Customer customer1 = em.find(Customer.class, customer.getId());

        logger.info(customer1);

        em.getTransaction().commit();
        em.close();

    }

}
