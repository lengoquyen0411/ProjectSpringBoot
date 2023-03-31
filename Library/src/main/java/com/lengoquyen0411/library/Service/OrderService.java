package com.lengoquyen0411.library.Service;

import com.lengoquyen0411.library.Model.ShoppingCart;

public interface OrderService {
    void  saveOder(ShoppingCart cart);

    void acceptOrder(Long id);

    void cancelOrder(Long id);
}
