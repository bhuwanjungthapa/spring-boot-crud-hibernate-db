package WebUiCrud.Repository;

import WebUiCrud.Entity.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByOrderByFirstNameAsc();

    List<Customer> findByFirstNameContainingOrLastNameContainingOrEmailContainingIgnoreCase(String query, String query1, String query2);
}
