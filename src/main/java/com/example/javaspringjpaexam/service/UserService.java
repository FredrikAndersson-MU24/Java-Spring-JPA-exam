package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.*;
import com.example.javaspringjpaexam.entity.Channel;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.entity.User;
import com.example.javaspringjpaexam.mapper.PostMapper;
import com.example.javaspringjpaexam.mapper.UserMapper;
import com.example.javaspringjpaexam.repository.ChannelRepository;
import com.example.javaspringjpaexam.repository.PostRepository;
import com.example.javaspringjpaexam.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ChannelRepository channelRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository, ChannelRepository channelRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.channelRepository = channelRepository;
    }

    // Create
    public UserMinimalDTO addUser(UserCreationDTO newUser) {
        boolean exists = userRepository.existsByUsername(newUser.getUsername());
        if (!exists) {
            User user = UserMapper.INSTANCE.userCreationDTOToUser(newUser);
            return UserMapper.INSTANCE.userToUserMinimalDTO(userRepository.save(user));
        } else throw new DuplicateKeyException("Username already exists");

    }

    public PostMinimalDTO createPostOnUserId(Post newPost, long userId, long channelId) {
        Channel channel = channelRepository.findById(channelId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (channel != null && user != null) {
            newPost.setUser(user);
            newPost.setChannel(channel);
            postRepository.save(newPost);
            return PostMapper.INSTANCE.postToPostMinimalDTO(newPost);
        }
        return null;
    }

    //Read
    public List<UserMinimalDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserMinimalDTO).collect(Collectors.toList());
    }

    public UserDetailedDTO getUserById(long id) {
        return UserMapper.INSTANCE.userToUserDetailedDTO(userRepository.findById(id).orElse(null));
    }

    public List<UserMinimalDTO> getUsersByFreeText(String searchTerm) {
        String query = "%" + searchTerm + "%";
        List<User> users = userRepository.findUsers(query);
        return users.stream().map(UserMapper.INSTANCE::userToUserMinimalDTO).collect(Collectors.toList());
    }

    public List<UserMinimalDTO> getUsersByUsername(String searchTerm) {
        List<User> users = userRepository.findUsersByUsernameContainingIgnoreCase(searchTerm);
        return users.stream().map(UserMapper.INSTANCE::userToUserMinimalDTO).collect(Collectors.toList());
    }


    public List<PostDetailedDTO> getUsersDetailedPosts(long userId) {
        List<Post> posts = postRepository.findAllByUser_Id(userId);
        return posts.stream().map(PostMapper.INSTANCE::postToPostDetailedDTO).collect(Collectors.toList());
    }

    //Update
    public UserMinimalDTO updateUserById(User userToUpdate, long id) {
        User user = userRepository.findById(id).map(u -> {
            u.setUsername(userToUpdate.getUsername());
            u.setFirstName(userToUpdate.getFirstName());
            u.setLastName(userToUpdate.getLastName());
            return userRepository.save(u);
        }).orElse(null);
        return UserMapper.INSTANCE.userToUserMinimalDTO(user);
    }

    //Delete
    public void deleteUser(long id) throws BadRequestException {
        if (getUserById(id) != null) {
            userRepository.deleteById(id);
        } else throw new BadRequestException("User not found");
    }
}
