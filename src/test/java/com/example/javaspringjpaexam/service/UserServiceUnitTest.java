package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.UserCreationDTO;
import com.example.javaspringjpaexam.dto.UserMinimalDTO;
import com.example.javaspringjpaexam.entity.User;
import com.example.javaspringjpaexam.mapper.UserMapper;
import com.example.javaspringjpaexam.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    //Mocking the repository class, to isolate the test to the service class.
    @Mock
    private UserRepository userRepository;

    //The mocked repository class is injected into the service class
    @InjectMocks
    private UserService userService;

    //A UserCreationDTO is passed as an argument to addUser(). If the username does NOT already exist,
    //addUser() should return an instance of UserMinimalDTO.
    @Test
    public void addUserShouldReturnUserMinimalDTOIfUsernameDoesNotAlreadyExist() {

        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "TestFirstName", "TestLastName");
        User testUser = UserMapper.INSTANCE.userCreationDTOToUser(testUserCreationDTO);
        when(userRepository.save(any())).thenReturn(testUser);

        //Act
        UserMinimalDTO returnedUser = userService.addUser(testUserCreationDTO);

        //Assert
        assertEquals("TestUsername", returnedUser.getUsername());
        verify(userRepository).save(any());

    }

    //A UserCreationDTO is passed as an argument to addUser(). If the username DOES already exist,
    //addUser() should throw a DuplicateKeyException.
    @Test
    public void addUserShouldThrowDuplicateKeyExceptionIfUsernameAlreadyExist() {

        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "TestFirstName", "TestLastName");
        when(userRepository.existsByUsernameIgnoreCase(any())).thenReturn(true);

        //Act & Assert
        assertThrows(DuplicateKeyException.class, () -> userService.addUser(testUserCreationDTO));
        verify(userRepository).existsByUsernameIgnoreCase(any());

    }

}