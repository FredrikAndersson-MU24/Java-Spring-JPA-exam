package com.example.javaspringjpaexam.mapper;

import com.example.javaspringjpaexam.dto.UserDTO;
import com.example.javaspringjpaexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

}
