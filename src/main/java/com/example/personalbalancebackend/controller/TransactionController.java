package com.example.personalbalancebackend.controller;

import com.example.personalbalancebackend.entity.Transaction;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.TransactionMapper;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.model.TransactionDTO;
import com.example.personalbalancebackend.service.LedgerService;
import com.example.personalbalancebackend.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@Service
@RequestMapping("api/v1/ledgers/{ledgerId}/transactions")
public class TransactionController {
    private TransactionService transactionService;
    private LedgerService ledgerService;

    public TransactionController(TransactionService transactionService, LedgerService ledgerService) {
        this.transactionService = transactionService;
        this.ledgerService = ledgerService;
    }

    @PostMapping()
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable UUID ledgerId, @Valid @RequestBody TransactionCreationDTO transactionCreationDTO)
            throws ResourceNotFoundException {
        Transaction transaction = transactionService.createTransaction(ledgerId, transactionCreationDTO);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toDTO(transaction));
    }

    /**
     * Manually create user and ledger
     * @param ledgerId
     * @param count
     * @Param currency
     * @return
     * @throws ResourceNotFoundException
     */
    @PostMapping("/fake_income/{count}/{currency}")
    public ResponseEntity<List<TransactionDTO>> createTransactionFakeIncome(@PathVariable UUID ledgerId, @PathVariable int count, @PathVariable String currency) {
        // check ledger id if exists
        ledgerService.getLedgerById(ledgerId);

        List<Transaction> transactions = transactionService.createTransactionFakeData(ledgerId, count, currency, true);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toListDTO(transactions));
    }

    /**
     * Manually create user and ledger
     * @param ledgerId
     * @param count
     * @Param currency
     * @return
     * @throws ResourceNotFoundException
     */
    @PostMapping("/fake_expense/{count}/{currency}")
    public ResponseEntity<List<TransactionDTO>> createTransactionFakeExpense(@PathVariable UUID ledgerId, @PathVariable int count, @PathVariable String currency) {
        // check ledger id if exists
        ledgerService.getLedgerById(ledgerId);

        List<Transaction> transactions = transactionService.createTransactionFakeData(ledgerId, count, currency, false);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toListDTO(transactions));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@PathVariable UUID ledgerId,
                                                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                   @RequestParam(required = false, defaultValue = "10") Integer size)
            throws ResourceNotFoundException {
        // check ledger id if exists
        ledgerService.getLedgerById(ledgerId);

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<Transaction> transactions = transactionService.getAllTransactionsByLedgerId(ledgerId, pageable);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toListDTO(transactions));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable UUID ledgerId, @PathVariable UUID transactionId)
            throws ResourceNotFoundException {
        // check ledger id if exists
        ledgerService.getLedgerById(ledgerId);

        Transaction transaction = transactionService.getTransactionsByLedgerIdAndTxId(ledgerId, transactionId);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toDTO(transaction));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<List<TransactionDTO>> deleteTransactionById(@PathVariable UUID ledgerId, @PathVariable UUID transactionId) throws ResourceNotFoundException {
        // check ledger id if exists
        ledgerService.getLedgerById(ledgerId);

        List<Transaction> transactions = transactionService.deleteTransactionById(ledgerId, transactionId);
        return ResponseEntity.ok().body(TransactionMapper.INSTANCE.toListDTO(transactions));
    }
}
