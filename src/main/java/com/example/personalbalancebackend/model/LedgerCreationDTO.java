package com.example.personalbalancebackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class LedgerCreationDTO {
    private String name;
    private UUID userId;
}
