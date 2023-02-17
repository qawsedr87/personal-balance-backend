package com.example.personalbalancebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
public class TransactionCategoryDTO {
    @JsonProperty("categoryId")
    private UUID id;
    private String name;
}
