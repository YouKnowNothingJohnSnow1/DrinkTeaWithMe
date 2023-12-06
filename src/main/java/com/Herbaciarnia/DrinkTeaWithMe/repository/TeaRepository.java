package com.Herbaciarnia.DrinkTeaWithMe.repository;

import com.Herbaciarnia.DrinkTeaWithMe.model.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long> {


    Optional<Tea> findById(Long id);
}
