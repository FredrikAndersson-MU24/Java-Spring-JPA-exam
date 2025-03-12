package com.example.javaspringjpaexam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.name", target = "postedBy")
    PostDTO postToPostDTO(Post post);

    Post postDTOToPost(PostDTO postDto);

}
