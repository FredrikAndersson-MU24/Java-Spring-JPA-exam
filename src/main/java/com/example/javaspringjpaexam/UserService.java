package com.example.javaspringjpaexam;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findUsersByNameContainingIgnoreCase(name);
    }

    public User updateUserById(User userToUpdate, Long id) {
        return userRepository.findById(id).map(u ->
        {
            u.setName(userToUpdate.getName());
            return userRepository.save(u);
        }).orElse(null);
    }


}
