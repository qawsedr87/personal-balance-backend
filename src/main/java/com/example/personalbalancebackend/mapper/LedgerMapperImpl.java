package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.Ledger;
import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.model.LedgerCreationDTO;
import com.example.personalbalancebackend.model.LedgerDTO;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.stream.Collectors;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public class LedgerMapperImpl implements LedgerMapper {

    @Override
    public Ledger toEntity(LedgerDTO dto) {
        return null;
    }

    @Override
    public LedgerDTO toDTO(Ledger entity) {
        return LedgerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .userId(entity.getUser().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public List<Ledger> toListEntity(List<LedgerDTO> dtos) {
        return null;
    }

    @Override
    public List<LedgerDTO> toListDTO(List<Ledger> entities) {
        return entities.stream().map(e -> INSTANCE.toDTO(e)).collect(Collectors.toList());
    }

    @Override
    public Ledger toEntity(LedgerCreationDTO dto, User user) {
        return new Ledger(dto.getName(), user);
    }
}
