package com.lengoquyen0411.library.Repo;

import com.lengoquyen0411.library.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}