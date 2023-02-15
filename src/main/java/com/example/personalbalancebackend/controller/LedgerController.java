package com.example.personalbalancebackend.controller;

import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.exception.ResourceNotFoundException;
import com.example.personalbalancebackend.mapper.LedgerMapper;
import com.example.personalbalancebackend.model.LedgerCreationDTO;
import com.example.personalbalancebackend.model.LedgerDTO;
import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.service.LedgerService;
import com.example.personalbalancebackend.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Service
@RequestMapping("api/v1/ledgers")
public class LedgerController {
    private LedgerService ledgerService;
    private UserService userService;

    public LedgerController(LedgerService ledgerService, UserService userService) {
        this.ledgerService = ledgerService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<LedgerDTO> createLedger(@Valid @RequestBody LedgerCreationDTO ledgerCreationDTO)
            throws ResourceNotFoundException {
        User user = userService.getUserById(ledgerCreationDTO.getUserId());
        Ledger ledger = LedgerMapper.INSTANCE.toEntity(ledgerCreationDTO, user);

        return ResponseEntity.ok().body(LedgerMapper.INSTANCE.toDTO(
                ledgerService.createLedger(ledger)
        ));
    }

    @GetMapping()
    public List<LedgerDTO> getLedgers() {
        List<Ledger> ledgers = ledgerService.getAllLedger();
        return LedgerMapper.INSTANCE.toListDTO(ledgers);
    }
}
