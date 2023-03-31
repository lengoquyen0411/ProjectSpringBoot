package com.lengoquyen0411.library.Service.impl;

import com.lengoquyen0411.library.DTO.CustomerDTO;
import com.lengoquyen0411.library.Model.Customer;
import com.lengoquyen0411.library.Repo.CustomerRepo;
import com.lengoquyen0411.library.Repo.RoleRepo;
import com.lengoquyen0411.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setRoles(Arrays.asList(roleRepo.findByName("CUSTOMER")));

        Customer customerSave = customerRepo.save(customer);
        return mapperDTO(customerSave);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepo.findByUsername(username);
    }

    @Override
    public Customer saveInfor(Customer customer) {
        Customer customer1 = customerRepo.findByUsername(customer.getUsername());
        customer1.setAddress(customer.getAddress());
        customer1.setCity(customer.getCity());
        customer1.setCountry(customer.getCountry());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customerRepo.save(customer1);
    }

    private CustomerDTO mapperDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setUsername(customer.getUsername());
        return customerDTO;
    }
}
