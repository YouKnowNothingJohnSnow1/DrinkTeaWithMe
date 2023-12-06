package com.Herbaciarnia.DrinkTeaWithMe.repository;

import com.Herbaciarnia.DrinkTeaWithMe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
