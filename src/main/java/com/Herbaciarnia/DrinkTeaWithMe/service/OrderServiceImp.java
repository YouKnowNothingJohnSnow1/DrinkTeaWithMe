package com.Herbaciarnia.DrinkTeaWithMe.service;

import com.Herbaciarnia.DrinkTeaWithMe.dto.OrderDto;
import com.Herbaciarnia.DrinkTeaWithMe.exception.ResourceNotFoundException;
import com.Herbaciarnia.DrinkTeaWithMe.model.Address;
import com.Herbaciarnia.DrinkTeaWithMe.model.Order;
import com.Herbaciarnia.DrinkTeaWithMe.model.User;
import com.Herbaciarnia.DrinkTeaWithMe.repository.AddressRepository;
import com.Herbaciarnia.DrinkTeaWithMe.repository.OrderRepository;
import com.Herbaciarnia.DrinkTeaWithMe.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderServiceImp implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Order.class);
    private static final Logger LOGGER2 = LoggerFactory.getLogger(User.class);


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, UserRepository userRepository,
                           AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        initializeData();
    }

    private void initializeData(){
        Order order1 = createOrder(LocalDate.of(2023,7,8), "Malgosia", "Pudlo",
                "Chrobrego 12/7","Przemysl", "22-090");
        Order order2 = createOrder(LocalDate.of(2023,1,19), "Marian", "Rum",
                "Zbojnika 22/1","Krakow", "15-880");

        LOGGER.info("Initialized orders: {}", Arrays.asList(order1, order2));
    }

    private Order createOrder( LocalDate localDate, String firstName, String lastName, String street, String city,
                               String zipCode ){
        Order order = new Order();
        order.setLocalDate(localDate);


        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        order.setUser(user);

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setZipCode(zipCode);
        address.setUser(user);
        user.setAddress(address);


        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public List<Order> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }




    public Order updateOrder(Long id, OrderDto orderRequestDto) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();

            order.setOrderId(orderRequestDto.getOrderId());

            if (orderRequestDto.getFirstName() != null) {
                order.getUser().setFirstName(orderRequestDto.getFirstName());
            }
            if (orderRequestDto.getLastName() != null) {
                order.getUser().setLastName(orderRequestDto.getLastName());
            }
            if(orderRequestDto.getStreet() != null ) {
                order.getUser().getAddress().setStreet(orderRequestDto.getStreet());
            }
            if(orderRequestDto.getCity() != null ){
                order.getUser().getAddress().setCity(orderRequestDto.getCity());
            }
            if(orderRequestDto.getZipCode() != null ){
                order.getUser().getAddress().setZipCode(orderRequestDto.getZipCode());
            }

            order.setLocalDate(orderRequestDto.getLocalDate());

            // Save the updated entity back to the database
            Order updatedOrder = orderRepository.save(order);

            return updatedOrder;  // Returning the updated Order entity
        } else {
            // Handle the case where the entity with the given id is not found
            throw new ResourceNotFoundException("Entity not found with id: " + id);
        }
    }

    @Override
    public void save(Order order) {

    }
    @Override
    public Order createOrder(Order orderRequest) {
        return orderRepository.save(orderRequest);
    }
}




