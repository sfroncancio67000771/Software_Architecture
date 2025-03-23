package com.example.demo.dto;

import lombok.Data;

@Data
public class PurchaseRequest {
    private String userEmail;
    private String isbn;
    private Integer quantity;
}
