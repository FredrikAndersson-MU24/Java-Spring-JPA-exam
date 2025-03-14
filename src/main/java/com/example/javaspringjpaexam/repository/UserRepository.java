package com.example.javaspringjpaexam.repository;

import com.example.javaspringjpaexam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByUsernameContainingIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE :query OR u.lastName LIKE :query OR u.username LIKE :query")
    List<User> findUsers(@Param("query") String query);


}
