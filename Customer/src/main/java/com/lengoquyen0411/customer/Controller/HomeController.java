package com.lengoquyen0411.customer.Controller;

import com.lengoquyen0411.library.DTO.ProductDTO;
import com.lengoquyen0411.library.Model.Category;
import com.lengoquyen0411.library.Model.Customer;
import com.lengoquyen0411.library.Model.ShoppingCart;
import com.lengoquyen0411.library.Service.CategoryService;
import com.lengoquyen0411.library.Service.CustomerService;
import com.lengoquyen0411.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session){
        if (principal != null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItem", cart.getTotalItems());
        }else {
            session.removeAttribute("username");
        }
        return "home";
    }
    @GetMapping("/home")
    public String index(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<ProductDTO> productDTOS = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDTOS);
        return "index";
    }
}
