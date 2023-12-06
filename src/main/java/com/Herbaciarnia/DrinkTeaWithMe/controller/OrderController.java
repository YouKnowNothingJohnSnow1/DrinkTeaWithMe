package com.Herbaciarnia.DrinkTeaWithMe.controller;

import com.Herbaciarnia.DrinkTeaWithMe.dto.OrderDto;
import com.Herbaciarnia.DrinkTeaWithMe.exception.ResourceNotFoundException;
import com.Herbaciarnia.DrinkTeaWithMe.model.Order;
import com.Herbaciarnia.DrinkTeaWithMe.service.OrderService;
import com.Herbaciarnia.DrinkTeaWithMe.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping
public class OrderController extends Validator {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/orders/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders().stream().map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrderById (@PathVariable ("id") Long id){
        List<Order> orders = orderService.getOrderById(id);

        if(orders.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        //convert entity to Dto
        OrderDto orderResponse = modelMapper.map(orders.get(0),  OrderDto.class);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        //convert dto to entity
        Order orderRequest = modelMapper.map(orderDto, Order.class);
        Order order = orderService.createOrder(orderRequest);

        //convert to entity
        OrderDto orderResponse = modelMapper.map(order, OrderDto.class);

        return new ResponseEntity<OrderDto>(orderResponse, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody OrderDto orderDto) {
        try {
            Order updatedOrder = orderService.updateOrder(id, orderDto);
            OrderDto updatedOrderDto = modelMapper.map(updatedOrder, OrderDto.class);
            return ResponseEntity.ok(updatedOrderDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

        @DeleteMapping("/orders/{id}")
        public ResponseEntity<OrderDto> deleteOrder (@PathVariable ("id") Long id){
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
    }
