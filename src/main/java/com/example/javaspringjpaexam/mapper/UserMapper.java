package com.example.javaspringjpaexam.mapper;

import com.example.javaspringjpaexam.dto.UserCreationDTO;
import com.example.javaspringjpaexam.dto.UserDetailedDTO;
import com.example.javaspringjpaexam.dto.UserMinimalDTO;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target ="id")
    UserMinimalDTO userToUserMinimalDTO(User user);

    @Mapping(expression = "java(getNumberOfPosts(user))", target = "postCount")
    UserDetailedDTO userToUserDetailedDTO(User user);

    default int getNumberOfPosts(User user) {
        List<Post> posts = user.getPosts();
        if (posts != null) {
            return posts.size();
        }
        return 0;
    }

    User userCreationDTOToUser (UserCreationDTO userCreationDTO);

}
