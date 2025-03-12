package com.example.javaspringjpaexam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.name", target = "postedBy")
    PostMinimalDTO postToPostMinimalDTO(Post post);

    @Mapping(source = "user.name", target = "postedBy")
    PostDetailedDTO postToPostDetailedDTO(Post post);

    Post postDTOToPost(PostMinimalDTO postMinimalDto);

}
