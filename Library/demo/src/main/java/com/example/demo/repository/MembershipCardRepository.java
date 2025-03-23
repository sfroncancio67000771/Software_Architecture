package com.example.demo.repository;

import com.example.demo.entities.MembershipCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipCardRepository extends JpaRepository<MembershipCard, Integer> {
    Optional <MembershipCard> findByUserEmail(String userEmail);
}
