package com.example.foro_hub.repository;


import com.example.foro_hub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}