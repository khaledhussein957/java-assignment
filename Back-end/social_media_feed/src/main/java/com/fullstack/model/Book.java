package com.fullstack.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
//    private Timestamp createdAt;
    // getters and setters
}
