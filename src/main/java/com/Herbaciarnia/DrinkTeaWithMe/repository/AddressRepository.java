package com.Herbaciarnia.DrinkTeaWithMe.repository;

import com.Herbaciarnia.DrinkTeaWithMe.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
