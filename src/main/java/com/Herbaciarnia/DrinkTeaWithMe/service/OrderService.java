package com.Herbaciarnia.DrinkTeaWithMe.service;
import com.Herbaciarnia.DrinkTeaWithMe.dto.OrderDto;
import com.Herbaciarnia.DrinkTeaWithMe.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getAllOrders();
    List<Order> getOrderById(Long id);
    void deleteOrder(Long id);
    //List<Order>updateOrder(Long id, Order orderRequest);
    Order createOrder(Order orderRequest);
    Order updateOrder(Long id, OrderDto orderRequestDto);

    void save(Order order);
}



/*

 */