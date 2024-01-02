package pl.javaps.dao;

import org.springframework.stereotype.Component;
import pl.javaps.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerDao {

    @PersistenceContext
    private EntityManager em;


    public Customer getCustomer(Long id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public List<Customer> getCustomers() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }
}
