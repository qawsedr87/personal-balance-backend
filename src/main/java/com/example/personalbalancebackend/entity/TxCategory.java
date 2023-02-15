package com.example.personalbalancebackend.entity;

import com.example.personalbalancebackend.model.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tx_categories")
public class TxCategory extends AuditModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany( mappedBy = "txCategory")
    private List<Transaction> transactions;

    public TxCategory() {}

    public TxCategory(String name) {
        this.id = UUID.randomUUID();
        this.name = name.toLowerCase();
        this.transactions = new ArrayList<>();
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }
}
