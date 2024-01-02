package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Address;
import pl.javaps.hibernate.entity.AddressType;
import pl.javaps.hibernate.entity.Customer;
import pl.javaps.hibernate.entity.CustomerDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App40IdsMapping {
    private static Logger logger = LogManager.getLogger(App40IdsMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setBirthPlace("Warszawa");
//        customerDetails.setBirthDay(LocalDate.of(1989, 10, 22));
//        customerDetails.setFatherName("Jan");
//        customerDetails.setMotherName("Nina");
//        customerDetails.setPesel("12345678912");
//        customerDetails.setCustomer(customer);
//        customer.setCustomerDetails(customerDetails);
        //em.persist(customer);

        logger.info(customer);
        logger.info(customer.getCustomerDetails());

        em.getTransaction().commit();
        em.close();

    }

}
