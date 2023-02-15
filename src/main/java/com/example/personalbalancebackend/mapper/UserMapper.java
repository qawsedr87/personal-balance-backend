package com.example.personalbalancebackend.mapper;

import com.example.personalbalancebackend.entity.User;
import com.example.personalbalancebackend.model.UserCreationDTO;
import com.example.personalbalancebackend.model.UserDTO;
import org.mapstruct.factory.Mappers;


public interface UserMapper extends GenericMapper<UserDTO, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserCreationDTO dto);
}
