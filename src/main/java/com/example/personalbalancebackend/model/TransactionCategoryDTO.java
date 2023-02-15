package com.example.personalbalancebackend.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
public class TransactionCategoryDTO {
    private UUID id;
    private String name;
}
