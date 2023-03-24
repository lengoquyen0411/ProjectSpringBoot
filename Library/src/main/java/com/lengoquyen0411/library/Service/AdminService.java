package com.lengoquyen0411.admin.Service;

import com.lengoquyen0411.admin.DTO.AdminDTO;
import com.lengoquyen0411.admin.Model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDTO adminDto);
}