package com.agridence.microservice.Assignment.Repository;

import com.agridence.microservice.Assignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}


