package com.example.foro_hub.repository;

import com.example.foro_hub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}