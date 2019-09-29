package ir.shayandaneshvar.springmvcexample.repository;

import ir.shayandaneshvar.springmvcexample.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
