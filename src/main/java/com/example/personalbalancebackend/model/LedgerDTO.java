package com.example.personalbalancebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class LedgerDTO {
    @JsonProperty("ledgerId")
    private UUID id;
    private String name;
    private UUID userId;
}
