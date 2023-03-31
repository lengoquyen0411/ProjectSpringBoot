package com.lengoquyen0411.library.Service.impl;

import com.lengoquyen0411.library.Model.CartItem;
import com.lengoquyen0411.library.Model.Order;
import com.lengoquyen0411.library.Model.OrderDetail;
import com.lengoquyen0411.library.Model.ShoppingCart;
import com.lengoquyen0411.library.Repo.CartItemRepo;
import com.lengoquyen0411.library.Repo.OrderDetailRepo;
import com.lengoquyen0411.library.Repo.OrderRepo;
import com.lengoquyen0411.library.Repo.ShoppingCartRepo;
import com.lengoquyen0411.library.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ShoppingCartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Override
    public void saveOder(ShoppingCart cart) {
        Order order = new Order();
        order.setOrderStatus("PENDING");
        order.setOrderDate(new Date());
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrices());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartItem item : cart.getCartItem()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getCostPrice());
            orderDetailRepo.save(orderDetail);
            orderDetailList.add(orderDetail);
            cartItemRepo.delete(item);
        }
        order.setOrderDetailList(orderDetailList);
        cart.setCartItem(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrices(0);
        cartRepo.save(cart);
        orderRepo.save(order);
    }

    @Override
    public void acceptOrder(Long id) {
        Order order = orderRepo.getById(id);
        order.setDeliveryDate(new Date());
        order.setOrderStatus("SHIPPING");
        orderRepo.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
