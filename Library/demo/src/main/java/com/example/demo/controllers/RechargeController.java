package com.example.demo.controllers;

import com.example.demo.entities.MembershipCard;
import com.example.demo.services.RechargeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @PostMapping("/membership-card")
    public ResponseEntity<String> rechargeMembershipCard(
            @RequestParam String userEmail,
            @RequestParam BigDecimal amount) {
        rechargeService.rechargeMembershipCard(userEmail, amount);
        return ResponseEntity.ok("Membership card recharged successfully!" + amount+"$");
    }


    @PostMapping("/create")
    public MembershipCard createMembershipCard(@RequestParam String userEmail) {
        return rechargeService.createMembershipCard(userEmail);
    }
    
    @PutMapping("/edit")
    public MembershipCard editMembershipCard(@RequestParam String userEmail,
                                             @RequestParam String newEmail,
                                             @RequestParam String newCardNumber) {
        return rechargeService.editMembershipCard(userEmail, newEmail, newCardNumber);
    }

}
