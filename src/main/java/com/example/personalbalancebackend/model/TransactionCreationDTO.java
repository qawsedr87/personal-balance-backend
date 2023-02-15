package com.example.personalbalancebackend.model;

import com.example.personalbalancebackend.entity.TxSourceEnum;
import com.example.personalbalancebackend.entity.TxTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionCreationDTO {
    private BigDecimal amount;
    private String currency;
    private TxTypeEnum type;
    private TxSourceEnum source;
    private String memo;
    private String category;
}
