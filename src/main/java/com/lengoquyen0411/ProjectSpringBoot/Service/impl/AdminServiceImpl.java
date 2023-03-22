package com.lengoquyen0411.ProjectSpringBoot.Service.impl;

import com.lengoquyen0411.ProjectSpringBoot.DTO.AdminDTO;
import com.lengoquyen0411.ProjectSpringBoot.Model.Admin;
import com.lengoquyen0411.ProjectSpringBoot.Repo.AdminRepo;
import com.lengoquyen0411.ProjectSpringBoot.Repo.RoleRepo;
import com.lengoquyen0411.ProjectSpringBoot.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDTO adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
