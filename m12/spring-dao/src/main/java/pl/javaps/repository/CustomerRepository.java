package pl.javaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javaps.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
