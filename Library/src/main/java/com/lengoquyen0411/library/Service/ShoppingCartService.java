package com.lengoquyen0411.library.Service;

import com.lengoquyen0411.library.Model.Customer;
import com.lengoquyen0411.library.Model.Product;
import com.lengoquyen0411.library.Model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);
}
