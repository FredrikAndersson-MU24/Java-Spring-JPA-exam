package com.example.javaspringjpaexam;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository, UserService userService, UserRepository userRepository) {
        this.channelRepository = channelRepository;
    }

    // Create
    public Channel createChannel(Channel newChannel) {
        return channelRepository.save(newChannel);
    }

    //Read
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannelById(long id) {
        return channelRepository.findById(id).orElse(null);
    }

    public List<Channel> getChannelsByFreeText(String searchTerm) {
        return channelRepository.findChannelsByNameContainingIgnoreCase(searchTerm);
    }

    //Update
    public Channel updateChannelByID(Channel channelToUpdate, long id) {
        Channel channel = getChannelById(id);
        if (channel != null) {
            channel.setName(channelToUpdate.getName());
            return channelRepository.save(channel);
        }
        return null;
    }


    //Delete
    public void deleteChannelById(long id) throws BadRequestException {
        if(getChannelById(id) != null){
            channelRepository.deleteById(id);
        } else throw new BadRequestException("Channel not found");
    }
}
