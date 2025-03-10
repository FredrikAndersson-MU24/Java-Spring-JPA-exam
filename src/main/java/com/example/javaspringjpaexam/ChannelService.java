package com.example.javaspringjpaexam;

import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
}
