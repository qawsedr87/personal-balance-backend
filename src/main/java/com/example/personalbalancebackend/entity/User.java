package com.example.personalbalancebackend.entity;

import com.example.personalbalancebackend.model.AuditModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany( mappedBy = "user")
    private List<Ledger> ledgers;

    public User () {
        super(new Date(), new Date());
    }
    public User (String name) {
        super(new Date(), new Date());
        this.id = UUID.randomUUID();
        this.name = name;
        this.ledgers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"ledgers\":\"" + ledgers.toString() + '\"' +
                "}";
    }
}
