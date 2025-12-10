package com.rahul.verma.movierental.repository;

import com.rahul.verma.movierental.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    Optional<User> findByUserName(String userName);
}
