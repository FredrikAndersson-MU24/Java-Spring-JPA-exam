package com.example.javaspringjpaexam.repository;

import com.example.javaspringjpaexam.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Mapping(source ="user.id", target ="id")
    List<User> findUsersByUsernameContainingIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE :query OR u.lastName LIKE :query OR u.username LIKE :query")
    List<User> findUsers(@Param("query") String query);


    boolean existsByUsername(@NotNull @Size(max = 32, message ="Username can be max 32 chars") String username);
}
