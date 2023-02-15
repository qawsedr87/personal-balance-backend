package com.example.personalbalancebackend.service;

import com.example.personalbalancebackend.entity.*;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.TransactionMapper;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.repository.TransactionCategoryRepository;
import com.example.personalbalancebackend.repository.TransactionRepository;
import com.example.personalbalancebackend.service.utils.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

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


    public List<Transaction> createTransactionFakeData(UUID ledgerId, int count, String currency, boolean isIncome) {
        Ledger ledger = ledgerService.getLedgerById(ledgerId);
        TxTypeEnum type = isIncome ? TxTypeEnum.INCOME : TxTypeEnum.EXPENSE;

        String category = RandomGenerator.generateRandomCategoryTerm();
        List<TxCategory> txCategories = transactionCategoryRepository.findByName(category);

        TxCategory txCategory = txCategories.isEmpty()
                ? transactionCategoryRepository.save(new TxCategory(category))
                : txCategories.get(0);

        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BigDecimal amount =  RandomGenerator.bigDecimalBetween(BigDecimal.valueOf(10), BigDecimal.valueOf(1000));

            Transaction transaction = new Transaction(
                    amount,
                    currency,
                    type,
                    RandomGenerator.randomTxSource(isIncome),
                    RandomGenerator.generateRandomTerm(isIncome),
                    txCategory,
                    ledger
            );
            transaction.setCreatedAt(new Date());
            transaction.setUpdatedAt(new Date());

            transactions.add(transaction);
        }

        // bulk saving
        transactionRepository.saveAll(transactions);

        return transactions;
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
