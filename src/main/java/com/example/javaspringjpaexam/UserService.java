package com.example.javaspringjpaexam;

import org.apache.coyote.BadRequestException;
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
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Post createPostOnUserId(Post newPost, long userId, long channelId) {
        Channel channel = channelRepository.findById(channelId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (channel != null && user != null) {
            newPost.setUser(user);
            newPost.setChannel(channel);
            return postRepository.save(newPost);
        }
        return null;
    }

    //Read
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(long id) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.findById(id).orElse(null));
    }

    public List<UserDTO> getUsersByName(String name) {
        List<User> users = userRepository.findUsersByNameContainingIgnoreCase(name);
        return users.stream().map(UserMapper.INSTANCE::userToUserDTO).collect(Collectors.toList());
    }

    public List<Post> getUsersPosts(long userId) {
        return postRepository.findAllByUser_Id(userId);
    }

    //Update
    public UserDTO updateUserById(User userToUpdate, long id) {
        User user= userRepository.findById(id).map(u -> {
            u.setName(userToUpdate.getName());
            return userRepository.save(u);
        }).orElse(null);
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    //Delete
    public void deleteUser(long id) throws BadRequestException {
        if (getUserById(id) != null) {
            userRepository.deleteById(id);
        } else throw new BadRequestException("User not found");
    }
}
