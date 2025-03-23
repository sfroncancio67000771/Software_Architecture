package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate;

    @Transient
    private Integer stock;

    @OneToMany(mappedBy = "book")
    private List<Inventory> inventories;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }


    public Integer getStock() {
        if (inventories == null) {
            return 0;
        }
        return inventories.stream().mapToInt(Inventory::getQuantity).sum();
    }
}