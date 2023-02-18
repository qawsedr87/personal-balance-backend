package com.example.personalbalancebackend.mapper;


import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.entity.Transaction;
import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.model.TransactionCategoryDTO;
import com.example.personalbalancebackend.model.TransactionCreationDTO;
import com.example.personalbalancebackend.model.TransactionDTO;

import javax.annotation.processing.Generated;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction toEntity(TransactionCreationDTO dto, TxCategory txCategory, Ledger ledger) {
        return new Transaction(
                dto.getAmount(),
                dto.getCurrency(),
                dto.getType(),
                dto.getSource(),
                dto.getMemo(),
                txCategory,
                ledger,
                new Date(),
                new Date()
        );
    }

    @Override
    public Transaction toEntity(TransactionDTO dto) {
        return null;
    }

    @Override
    public TransactionDTO toDTO(Transaction entity) {
        TransactionCategoryDTO txCategoryDTO = TransactionCategoryDTO.builder().id(entity.getTxCategory().getId()).name(entity.getTxCategory().getName()).build();
        return TransactionDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .type(entity.getTxType())
                .source(entity.getTxSource())
                .memo(entity.getMemo())
                .category(txCategoryDTO)
                .ledgerId(entity.getLedger().getId())
                .userId(entity.getLedger().getUser().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public List<Transaction> toListEntity(List<TransactionDTO> dtos) {
        return null;
    }

    @Override
    public List<TransactionDTO> toListDTO(List<Transaction> entities) {
        return entities.stream().map(e -> INSTANCE.toDTO(e)).collect(Collectors.toList());
    }
}
