package com.example.javaspringjpaexam.repository;

import com.example.javaspringjpaexam.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    List<Channel> findChannelsByNameContainingIgnoreCase(String title);

    boolean existsByNameIgnoreCase(String name);
}
