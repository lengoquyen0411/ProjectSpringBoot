package com.lengoquyen0411.customer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String accountHome(){
        return "my-account";
    }
}
