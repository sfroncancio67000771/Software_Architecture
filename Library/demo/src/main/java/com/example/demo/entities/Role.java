package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Data
@Table(name ="roles")
@Entity
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = true)
    private String name;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;
}
