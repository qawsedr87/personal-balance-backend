package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.model.LedgerCreationDTO;
import com.example.personalbalancebackend.model.LedgerDTO;
import org.mapstruct.factory.Mappers;

public interface LedgerMapper extends GenericMapper<LedgerDTO, Ledger> {
    LedgerMapper INSTANCE = Mappers.getMapper(LedgerMapper.class);

    Ledger toEntity(LedgerCreationDTO dto, User user);
}
