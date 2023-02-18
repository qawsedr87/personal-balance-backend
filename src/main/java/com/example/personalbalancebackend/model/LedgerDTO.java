package com.example.personalbalancebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class LedgerDTO extends AuditModel {
    @JsonProperty("ledgerId")
    private UUID id;
    private String name;
    private UUID userId;

    @Builder
    public LedgerDTO(UUID id, String name, UUID userId, Date createdAt, Date updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
