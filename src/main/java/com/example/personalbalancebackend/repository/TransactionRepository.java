package com.example.personalbalancebackend.repository;

import com.example.personalbalancebackend.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findTransactionsByLedger_Id(UUID ledgerId, Pageable pageable);
}
