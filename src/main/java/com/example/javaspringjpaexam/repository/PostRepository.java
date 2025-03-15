package com.example.javaspringjpaexam.repository;

import com.example.javaspringjpaexam.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser_Id(long userId);

    List<Post> findAllByChannel_Id(long userId);

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCaseAndChannel_Id(String title, long channelId);
}
