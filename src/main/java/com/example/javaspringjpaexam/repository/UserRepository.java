package com.example.javaspringjpaexam.repository;

import com.example.javaspringjpaexam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByNameContainingIgnoreCase(String name);

}
