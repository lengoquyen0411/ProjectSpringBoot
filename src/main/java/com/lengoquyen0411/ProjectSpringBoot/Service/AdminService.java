package com.lengoquyen0411.ProjectSpringBoot.Service;

import com.lengoquyen0411.ProjectSpringBoot.DTO.AdminDTO;
import com.lengoquyen0411.ProjectSpringBoot.Model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDTO adminDto);
}