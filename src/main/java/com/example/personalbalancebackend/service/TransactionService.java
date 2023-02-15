package com.example.personalbalancebackend.service;

import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.entity.Transaction;
import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.TransactionMapper;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.repository.TransactionCategoryRepository;
import com.example.personalbalancebackend.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionCategoryRepository transactionCategoryRepository;
    private LedgerService ledgerService;

    public TransactionService(TransactionRepository transactionRepository, TransactionCategoryRepository transactionCategoryRepository, LedgerService ledgerService) {
        this.transactionRepository = transactionRepository;
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.ledgerService = ledgerService;
    }


    public Transaction createTransaction(UUID ledgerId, TransactionCreationDTO transactionCreationDTO) {
        // create or get transaction category
        String category = transactionCreationDTO.getCategory();
        List<TxCategory> txCategories = transactionCategoryRepository.findByName(category);

        TxCategory txCategory = txCategories.isEmpty()
                ? transactionCategoryRepository.save(new TxCategory(category))
                : txCategories.get(0);


        Ledger ledger = ledgerService.getLedgerById(ledgerId);
        Transaction transaction = TransactionMapper.INSTANCE.toEntity(transactionCreationDTO, txCategory, ledger);

        transaction.setCreatedAt(new Date());
        transaction.setUpdatedAt(new Date());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsByLedgerId(UUID ledgerId) {
        return transactionRepository.findTransactionsByLedger_Id(ledgerId);
    }

    public Transaction getTransactionsById(UUID transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId));
    }

    public Transaction getTransactionsByLedgerIdAndTxId(UUID ledgerId, UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId));


        if (!transaction.getLedger().getId().equals(ledgerId)) {
            throw new ResourceNotFoundException("Transaction", "ledger id", ledgerId);
        }

        return transaction;
    }

    public List<TxCategory> getAllTransactionCategories() {
        return transactionCategoryRepository.findAll();
    }
}
