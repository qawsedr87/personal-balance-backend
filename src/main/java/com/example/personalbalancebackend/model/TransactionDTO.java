package com.example.personalbalancebackend.model;

import com.example.personalbalancebackend.entity.TxSourceEnum;
import com.example.personalbalancebackend.entity.TxTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Builder
public class TransactionDTO {
    @JsonProperty("transactionId")
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private TxTypeEnum type;
    private TxSourceEnum source;
    private String memo;
    private TransactionCategoryDTO category;
    private UUID ledgerId;
    private UUID userId;
}
