package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.model.UserCreationDTO;
import com.example.personalbalancebackend.model.UserDTO;
import com.example.personalbalancebackend.model.UserLedgerDTO;

import javax.annotation.processing.Generated;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserCreationDTO dto) {
        return new User(dto.getName());
    }

    @Override
    public User toEntity(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO toDTO(User entity) {
        List<UserLedgerDTO> ledgers = entity.getLedgers().stream().map(
                ledger -> UserLedgerDTO.builder()
                        .id(ledger.getId())
                        .name(ledger.getName())
                        .build()
                ).toList();

        return UserDTO.builder().id(entity.getId()).name(entity.getName()).ledgers(ledgers).build();
    }

    @Override
    public List<User> toListEntity(List<UserDTO> dtos) {
        return null;
    }

    @Override
    public List<UserDTO> toListDTO(List<User> entities) {
        return entities.stream().map((e) -> INSTANCE.toDTO(e)).collect(toList());
    }
}
