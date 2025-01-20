package com.example.userservice.mappers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
