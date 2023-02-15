package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.model.TransactionCategoryDTO;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.stream.Collectors;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public class TransactionCategoryMapperImpl implements TransactionCategoryMapper {
    @Override
    public TxCategory toEntity(TransactionCategoryDTO dto) {
        return null;
    }

    @Override
    public TransactionCategoryDTO toDTO(TxCategory entity) {
        return TransactionCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<TxCategory> toListEntity(List<TransactionCategoryDTO> dtos) {
        return null;
    }

    @Override
    public List<TransactionCategoryDTO> toListDTO(List<TxCategory> entities) {
        return entities.stream().map(
                (e) -> INSTANCE.toDTO(e)).collect(Collectors.toList());
    }
}
