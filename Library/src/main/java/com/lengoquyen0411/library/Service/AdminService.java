package com.lengoquyen0411.library.Service;

import com.lengoquyen0411.library.DTO.AdminDTO;
import com.lengoquyen0411.library.Model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDTO adminDto);
}