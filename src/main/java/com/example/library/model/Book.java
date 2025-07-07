package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This annotation marks the class as a JPA entity, which means it will be mapped to a database table.
@Data // Lombok's @Data annotation generates getters, setters, toString, equals, and hashCode methods automatically.
@NoArgsConstructor // Lombok's @NoArgsConstructor generates a no-argument constructor.
@AllArgsConstructor // Lombok's @AllArgsConstructor generates a constructor with all fields as parameters.
public class Book {
    @Id @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private boolean available = true; 
}
