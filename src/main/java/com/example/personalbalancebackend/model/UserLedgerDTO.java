package com.example.personalbalancebackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class UserLedgerDTO {
    private UUID id;
    private String name;

//    public UserLedgerDTO(UUID id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
