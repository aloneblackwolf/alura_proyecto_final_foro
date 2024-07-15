package com.example.foro_hub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String status;

    @ManyToOne
    private User author;

    @ManyToOne
    private Course course;
}