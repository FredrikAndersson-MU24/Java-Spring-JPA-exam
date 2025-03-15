package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.*;
import com.example.javaspringjpaexam.entity.Channel;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.mapper.ChannelMapper;
import com.example.javaspringjpaexam.mapper.PostMapper;
import com.example.javaspringjpaexam.repository.ChannelRepository;
import com.example.javaspringjpaexam.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final PostRepository postRepository;

    public ChannelService(ChannelRepository channelRepository, PostRepository postRepository) {
        this.channelRepository = channelRepository;
        this.postRepository = postRepository;
    }

    // Create
    public ChannelDTO createChannel(ChannelCreationDTO newChannel) {
        boolean titleExists = channelRepository.existsByNameIgnoreCase(newChannel.getName());
        if (!titleExists) {
            Channel channel = ChannelMapper.INSTANCE.channelCreationDTOToChannel(newChannel);
            return ChannelMapper.INSTANCE.channelToChannelDTO(channelRepository.save(channel));
        } else throw new DuplicateKeyException("Channel name already exists");
    }

    //Read
    public List<ChannelDTO> getAllChannels() {
        return channelRepository.findAll().stream().map(ChannelMapper.INSTANCE::channelToChannelDTO).collect(Collectors.toList());
    }

    public ChannelDTO getChannelById(long id) {
        return ChannelMapper.INSTANCE.channelToChannelDTO(channelRepository.findById(id).orElse(null));
    }

    public List<ChannelDTO> getChannelsByFreeText(String searchTerm) {
        List<Channel> channels = channelRepository.findChannelsByNameContainingIgnoreCase(searchTerm);

        return channels.stream().map(ChannelMapper.INSTANCE::channelToChannelDTO).toList();
    }

    public List<PostDetailedDTO> getAllPostDetailedDTOByChannelId(long channelId) {
        List<Post> posts = postRepository.findAllByChannel_Id(channelId);
        return posts.stream().map(PostMapper.INSTANCE::postToPostDetailedDTO).toList();
    }

    public List<PostMinimalDTO> getAllPostMinimalDTOByChannelId(long channelId) {
        List<Post> posts = postRepository.findAllByChannel_Id(channelId);
        return posts.stream().map(PostMapper.INSTANCE::postToPostMinimalDTO).toList();
    }

    //Update
    public ChannelDTO updateChannelByID(ChannelCreationDTO channelToUpdate, long id) {
        boolean titleExists = channelRepository.existsByNameIgnoreCase(channelToUpdate.getName());
        if (!titleExists) {
            Channel channel = channelRepository.findById(id).orElse(null);
            if (channel != null) {
                channel.setName(channelToUpdate.getName());
                return ChannelMapper.INSTANCE.channelToChannelDTO(channelRepository.save(channel));
            }else throw new EntityNotFoundException("Channel ID not found");
        } else throw new DuplicateKeyException("Channel name already exists");
    }

    //Delete
    public void deleteChannelById(long id) {
        if (getChannelById(id) != null) {
            channelRepository.deleteById(id);
        } else throw new EntityNotFoundException("Channel ID not found");
    }
}
