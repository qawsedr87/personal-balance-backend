package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.model.TransactionCategoryDTO;
import org.mapstruct.factory.Mappers;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public interface TransactionCategoryMapper extends GenericMapper<TransactionCategoryDTO, TxCategory> {
    TransactionCategoryMapper INSTANCE = Mappers.getMapper(TransactionCategoryMapper.class);
}
