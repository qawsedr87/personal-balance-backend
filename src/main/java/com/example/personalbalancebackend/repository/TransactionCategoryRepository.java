package com.example.personalbalancebackend.repository;

import com.example.personalbalancebackend.entity.TxCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TxCategory, UUID> {
    List<TxCategory> findByName(String name);
}
