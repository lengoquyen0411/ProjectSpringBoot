package com.lengoquyen0411.ProjectSpringBoot.Repo;

import com.lengoquyen0411.ProjectSpringBoot.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}