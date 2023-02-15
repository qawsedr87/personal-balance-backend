package com.example.personalbalancebackend.model;

import com.example.personalbalancebackend.entity.Ledger;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private List<UserLedgerDTO> ledgers;

//    public UserDTO(UUID id, String name, List<UserLedgerDTO> ledgers) {
//        this.id = id;
//        this.name = name;
//        this.ledgers = ledgers;
//    }
}
