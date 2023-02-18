package com.example.personalbalancebackend.model;

import com.example.personalbalancebackend.entity.TxSourceEnum;
import com.example.personalbalancebackend.entity.TxTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class TransactionDTO extends AuditModel {
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

    @Builder
    public TransactionDTO(UUID id, BigDecimal amount,
                          String currency,
                          TxTypeEnum type,
                          TxSourceEnum source,
                          String memo,
                          TransactionCategoryDTO category,
                          UUID ledgerId,
                          UUID userId,
                          Date createdAt,
                          Date updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.type = type;
        this.source = source;
        this.memo = memo;
        this.category = category;
        this.ledgerId = ledgerId;
        this.userId = userId;
    }
}
