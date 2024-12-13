package com.example.homesphere_back.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Property")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String neighboorhood;
    private String region;
    private String publicationType;
    private String propertyType;
    private int size;
    private double value;
    private int bathrooms;
    private int bedrooms;
    private String description;
    private String number;
    private String email;
    private int likes;
}
