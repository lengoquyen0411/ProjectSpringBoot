package com.lengoquyen0411.library.Repo;

import com.lengoquyen0411.library.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
