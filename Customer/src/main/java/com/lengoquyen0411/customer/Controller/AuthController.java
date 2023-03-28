package com.lengoquyen0411.customer.Controller;

import com.lengoquyen0411.library.DTO.CustomerDTO;
import com.lengoquyen0411.library.Model.Customer;
import com.lengoquyen0411.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDTO", new CustomerDTO());
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDTO")CustomerDTO customerDTO,
                                  BindingResult result,
                                  Model model){
        try{
            if (result.hasErrors()){
                model.addAttribute("customerDTO", customerDTO);
                return "register";
            }
            Customer customer = customerService.findByUsername(customerDTO.getUsername());
            if (customer != null){
                model.addAttribute("username", "Username have been registered");
                model.addAttribute("customerDTO", customerDTO);
                return "register";
            }
            if (customerDTO.getPassword().equals(customerDTO.getRepeatPassword())){
                customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
                customerService.save(customerDTO);
                model.addAttribute("success", "Register Successfully");
                return "register";
            }else {
                model.addAttribute("password", "Password is not same");
                model.addAttribute("customerDTO", customerDTO);
                return "register";
            }
        }catch (Exception e){
            model.addAttribute("error", "Server have ran some problems");
            model.addAttribute("customerDTO", customerDTO);
        }
        return "register";
    }
}
