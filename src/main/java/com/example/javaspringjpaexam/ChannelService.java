package com.example.javaspringjpaexam;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannelById(long id) {
        return channelRepository.findById(id).orElse(null);
    };

    public Channel createChannel(Channel newChannel) {
        return channelRepository.save(newChannel);
    }

    public void deleteChannelById(long id) throws BadRequestException {
        if(getChannelById(id) != null){
            channelRepository.deleteById(id);
        } else throw new BadRequestException("User not found");
    }
}
