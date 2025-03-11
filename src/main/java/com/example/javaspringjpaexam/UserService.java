package com.example.javaspringjpaexam;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private ChannelRepository channelRepository;

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
        User user = getUserById(userId);
        if (channel != null && user != null) {
            newPost.setUser(user);
            newPost.setChannel(channel);
            return postRepository.save(newPost);
        }
        return null;
    }

    //Read
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findUsersByNameContainingIgnoreCase(name);
    }

    public List<Post> getUsersPosts(long userId) {
        return postRepository.findAllByUser_Id(userId);
    }

    //Update
    public User updateUserById(User userToUpdate, long id) {
        return userRepository.findById(id).map(u -> {
            u.setName(userToUpdate.getName());
            return userRepository.save(u);
        }).orElse(null);
    }

    //Delete
    public void deleteUser(long id) throws BadRequestException {
        if (getUserById(id) != null) {
            userRepository.deleteById(id);
        } else throw new BadRequestException("User not found");
    }
}
