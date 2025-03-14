package com.example.javaspringjpaexam.mapper;

import com.example.javaspringjpaexam.dto.PostCreationDTO;
import com.example.javaspringjpaexam.dto.PostDetailedDTO;
import com.example.javaspringjpaexam.dto.PostMinimalDTO;
import com.example.javaspringjpaexam.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.username", target = "postedBy")
    PostMinimalDTO postToPostMinimalDTO(Post post);

    @Mapping(source = "user.username", target = "postedBy")
    PostDetailedDTO postToPostDetailedDTO(Post post);

    Post postCreationDTOToPost(PostCreationDTO postCreationDTO);

}
