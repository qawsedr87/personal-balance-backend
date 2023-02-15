package com.example.personalbalancebackend.service;

import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.model.LedgerCreationDTO;
import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class LedgerService {
    @Autowired
    private LedgerRepository ledgerRepository;

    public Ledger createLedger(Ledger newLedger) {
        newLedger.setId(UUID.randomUUID());
        newLedger.setCreatedAt(new Date());
        newLedger.setUpdatedAt(new Date());
        return this.ledgerRepository.save(newLedger);
    }

    public List<Ledger> getAllLedger() {
        return this.ledgerRepository.findAll();
    }

    public Ledger getLedgerById(UUID ledgerId) {
        return this.ledgerRepository.findById(ledgerId)
                .orElseThrow(() -> new ResourceNotFoundException("Ledger", "id", ledgerId));
    }
}
