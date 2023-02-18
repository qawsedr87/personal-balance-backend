package com.example.personalbalancebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class UserDTO extends AuditModel {
    @JsonProperty("userId")
    private UUID id;
    private String name;
    private List<UserLedgerDTO> ledgers;

    @Builder
    public UserDTO(UUID id, String name, List<UserLedgerDTO> ledgers, Date createdAt, Date updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.ledgers = ledgers;
    }
}
