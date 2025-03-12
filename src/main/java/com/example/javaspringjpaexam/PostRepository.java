package com.example.javaspringjpaexam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser_Id(long userId);

    List<Post> findAllByChannel_Id(long userId);

}
