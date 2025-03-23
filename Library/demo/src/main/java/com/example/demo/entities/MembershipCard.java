package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "membership_cards")
public class MembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false, unique = true)
    private User user;

    @Column(unique = true, nullable = false, length = 16) // Tarjeta con longitud fija
    private String cardNumber;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(nullable = false)
    private boolean status = true;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        if (this.balance == null) {
            this.balance = BigDecimal.ZERO;
        }
    }
}
