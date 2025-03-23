package com.example.demo.controllers;

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.services.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/book")
    public String bookPurchase(@RequestBody PurchaseRequest request) {
        purchaseService.purchaseBook(request.getUserEmail(), request.getIsbn(), request.getQuantity());
        return "Purchased Successfully";
    }
}
