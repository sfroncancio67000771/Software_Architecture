package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.MembershipCard;
import com.example.demo.entities.User;
import com.example.demo.repository.MembershipCardRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RechargeService {
    @Autowired
    private MembershipCardRepository membershipCardRepository;
    @Autowired
    private UserRepository userRepository;

    public void rechargeMembershipCard(String userEmail, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(50000)) < 0 || amount.compareTo(BigDecimal.valueOf(200000)) > 0) {
            throw new RuntimeException("The recharge amount must be between $50,000 and $200,000.");
        }

        Optional<MembershipCard> cardOptional = membershipCardRepository.findByUserEmail(userEmail);
        MembershipCard card = cardOptional.orElseThrow(() ->
                new RuntimeException("Membership card not found for user: " + userEmail));

        card.setBalance(card.getBalance().add(amount));
        membershipCardRepository.save(card);
    }


    public MembershipCard createMembershipCard(String userEmail) {
        Optional<MembershipCard> cardOptional = membershipCardRepository.findByUserEmail(userEmail);
        if (cardOptional.isPresent()) {
            throw new RuntimeException("Membership card already exists for user: " + userEmail);
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        MembershipCard card = new MembershipCard();
        card.setUser(user);
        card.setBalance(BigDecimal.ZERO);
        return membershipCardRepository.save(card);
    }


    public MembershipCard editMembershipCard(String userEmail, String newEmail, String newCardNumber) {
        Optional<MembershipCard> cardOptional = membershipCardRepository.findByUserEmail(userEmail);
        MembershipCard card = cardOptional.orElseThrow(() ->
                new RuntimeException("Membership card not found for user: " + userEmail));

        if (newEmail == null || newEmail.isEmpty()) {
            throw new RuntimeException("New email cannot be empty.");
        }

        if (newCardNumber == null || newCardNumber.isEmpty()) {
            throw new RuntimeException("New card number cannot be empty.");
        }

        card.getUser().setEmail(newEmail);
        card.setCardNumber(newCardNumber);

        return membershipCardRepository.save(card);
    }
}

