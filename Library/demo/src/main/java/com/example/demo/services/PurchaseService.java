package com.example.demo.services;

import com.example.demo.entities.Inventory;
import com.example.demo.entities.MembershipCard;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.MembershipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PurchaseService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MembershipCardRepository membershipCardRepository;

    @Transactional
    public void purchaseBook(String userEmail, String isbn, int quantity) {
        Inventory inventory = inventoryRepository.findByBookIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book with ISBN " + isbn + " not found in inventory."));

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for book with ISBN " + isbn);
        }

        MembershipCard card = membershipCardRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Membership card not found for user: " + userEmail));


        BigDecimal totalCost = inventory.getBook().getPrice().multiply(BigDecimal.valueOf(quantity));


        if (card.getBalance().compareTo(totalCost) < 0) {
            throw new RuntimeException("Insufficient balance on the membership card.");
        }


        inventory.setQuantity(inventory.getQuantity() - quantity);
        card.setBalance(card.getBalance().subtract(totalCost));

        inventoryRepository.save(inventory);
        membershipCardRepository.save(card);
    }
}
