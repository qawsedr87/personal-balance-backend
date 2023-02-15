package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.entity.Transaction;
import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.model.TransactionDTO;
import org.mapstruct.factory.Mappers;

public interface TransactionMapper extends GenericMapper<TransactionDTO, Transaction> {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionCreationDTO dto, TxCategory txCategory, Ledger ledger);
}
