package com.example.javaspringjpaexam;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    ChannelDTO channelToChannelDTO (Channel channel);
}
