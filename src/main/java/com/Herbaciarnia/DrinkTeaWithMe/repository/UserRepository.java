package com.Herbaciarnia.DrinkTeaWithMe.repository;

import com.Herbaciarnia.DrinkTeaWithMe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
