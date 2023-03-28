package com.lengoquyen0411.library.Service;

import com.lengoquyen0411.library.DTO.CustomerDTO;
import com.lengoquyen0411.library.Model.Customer;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
    Customer findByUsername(String username);
}
