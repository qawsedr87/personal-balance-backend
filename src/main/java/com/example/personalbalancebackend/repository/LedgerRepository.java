package com.example.personalbalancebackend.repository;

import com.example.personalbalancebackend.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, UUID> {
}
