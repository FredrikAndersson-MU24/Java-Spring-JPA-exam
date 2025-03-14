package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.ChannelDTO;
import com.example.javaspringjpaexam.dto.PostDetailedDTO;
import com.example.javaspringjpaexam.dto.PostMinimalDTO;
import com.example.javaspringjpaexam.entity.Channel;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.mapper.ChannelMapper;
import com.example.javaspringjpaexam.mapper.PostMapper;
import com.example.javaspringjpaexam.repository.ChannelRepository;
import com.example.javaspringjpaexam.repository.PostRepository;
import com.example.javaspringjpaexam.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final PostRepository postRepository;

    public ChannelService(ChannelRepository channelRepository, UserService userService, UserRepository userRepository, PostRepository postRepository) {
        this.channelRepository = channelRepository;
        this.postRepository = postRepository;
    }

    // Create
    public ChannelDTO createChannel(Channel newChannel) {
        return ChannelMapper.INSTANCE.channelToChannelDTO(channelRepository.save(newChannel));
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
    public ChannelDTO updateChannelByID(Channel channelToUpdate, long id) {
        Channel channel = channelRepository.findById(id).orElse(null);
        if (channel != null) {
            channel.setName(channelToUpdate.getName());
            return ChannelMapper.INSTANCE.channelToChannelDTO(channelRepository.save(channel));
        }
        return null;
    }

    //Delete
    public void deleteChannelById(long id) throws BadRequestException {
        if (getChannelById(id) != null) {
            channelRepository.deleteById(id);
        } else throw new BadRequestException("Channel not found");
    }
}
