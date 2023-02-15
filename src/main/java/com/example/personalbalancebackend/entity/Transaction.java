package com.example.personalbalancebackend.entity;

import com.example.personalbalancebackend.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends AuditModel {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(columnDefinition = "Decimal(19,3) default '0.00'", nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(length = 3, nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private TxTypeEnum txType;

    @Size(max = 250)
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private TxSourceEnum txSource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tx_category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TxCategory txCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ledger_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ledger ledger;

    public Transaction() {}
    public Transaction (BigDecimal amount, String currency, TxTypeEnum txType, TxSourceEnum txSource, String memo, TxCategory txCategory, Ledger ledger) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.currency = currency;
        this.txType = txType;
        this.txSource = txSource;
        this.memo = memo;
        this.txCategory = txCategory;
        this.ledger = ledger;
    }
}

