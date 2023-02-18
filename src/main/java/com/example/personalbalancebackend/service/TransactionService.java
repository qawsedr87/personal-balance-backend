package com.example.personalbalancebackend.service;

import com.example.personalbalancebackend.entity.*;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.TransactionMapper;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.repository.TransactionCategoryRepository;
import com.example.personalbalancebackend.repository.TransactionRepository;
import com.example.personalbalancebackend.service.utils.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
                    ledger,
                    new Date(),
                    new Date()
            );

            transactions.add(transaction);
        }

        // bulk saving
        transactionRepository.saveAll(transactions);

        return transactions;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsByLedgerId(UUID ledgerId, Pageable paging) {

        Page<Transaction> pagedResult = transactionRepository.findTransactionsByLedger_Id(ledgerId, paging);

        if (paging.getPageNumber() > pagedResult.getTotalPages()) {
            throw new ResourceNotFoundException("Transaction", "page is out of range",
                    "total page is " + pagedResult.getTotalPages() + ", size of each page is " + pagedResult.getSize());
        }

        return pagedResult.toList();
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

    public List<Transaction> deleteTransactionById(UUID ledgerId, UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId));

        if (!transaction.getLedger().getId().equals(ledgerId)) {
            throw new ResourceNotFoundException("Transaction", "ledger id", ledgerId);
        }

        transactionRepository.deleteById(transactionId);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return getAllTransactionsByLedgerId(ledgerId, pageable);
    }

    public List<TxCategory> getAllTransactionCategories(Pageable paging) {

        Page<TxCategory> pagedResult = transactionCategoryRepository.findAll(paging);

        if (paging.getPageNumber() > pagedResult.getTotalPages()) {
            throw new ResourceNotFoundException("Transaction category", "page is out of range",
                    "total page is " + pagedResult.getTotalPages() + ", size of each page is " + pagedResult.getSize());
        }

        return pagedResult.toList();
    }
}
