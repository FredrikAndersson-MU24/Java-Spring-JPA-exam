package com.example.javaspringjpaexam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    @Mapping(expression = "java(getNumberOfPosts(channel.getPosts()))", target ="postCount")
    ChannelDTO channelToChannelDTO (Channel channel);

    default int getNumberOfPosts(List<Post> posts) {
        return posts.size();
    }

}
