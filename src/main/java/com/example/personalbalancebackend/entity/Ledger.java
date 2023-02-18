package com.example.personalbalancebackend.entity;

import com.example.personalbalancebackend.model.AuditModel;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "ledgers")
public class Ledger extends AuditModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany( mappedBy = "ledger")
    private List<Transaction> transactions;

    public Ledger () {
        super(new Date(), new Date());
    }
    public Ledger (String name, User user) {
        super(new Date(), new Date());
        this.id = UUID.randomUUID();
        this.name = name;
        this.user = user;
        this.transactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"user id\":\"" + user.getId() + '\"' +
                ", \"user name\":\"" + user.getName() + '\"' +
                "}";
    }
}


