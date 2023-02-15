package com.example.personalbalancebackend.repository;

import com.example.personalbalancebackend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionsByLedger_Id(UUID ledgerId);
}
