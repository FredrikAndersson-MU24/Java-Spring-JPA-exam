package com.example.javaspringjpaexam.controller;

import com.example.javaspringjpaexam.dto.UserCreationDTO;

import com.example.javaspringjpaexam.dto.UserMinimalDTO;
import com.example.javaspringjpaexam.entity.User;
import com.example.javaspringjpaexam.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    // Component tests for the addUser() method in the UserController. addUser() is called when making a POST request
    // on the "/users" end-point.


    // A series of component tests that tests if using a POST request with a valid UserCreationDTO request body returns
    // status code 200 and the created user.

    // Test with a valid request body, where username length is shorter than the maximum allowed number of characters
    @Test
    void addUserShouldRespondWithUserMinimalDTOAndStatusOk() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "Test", "TestLastName");
        User user = objectMapper.readValue(objectMapper.writeValueAsString(testUserCreationDTO), User.class);
        when(userRepository.save(any())).thenReturn(user);

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("TestUsername"));

        verify(userRepository, times(1)).save(any()); // Verify that userRepository.save is invoked one time

    }

    // Test an edge case where username length is of the maximum allowed number of characters(32)
    @Test
    void addUserShouldRespondWithUserMinimalDTOAndStatusOkWhenUsernameIsMaxLength() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsernameTestUsernameTestUser", "Test", "TestLastName");
        User user = objectMapper.readValue(objectMapper.writeValueAsString(testUserCreationDTO), User.class);
        when(userRepository.save(any())).thenReturn(user);

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("TestUsernameTestUsernameTestUser"));

        verify(userRepository, times(1)).save(any()); // Verify that userRepository.save is invoked one time

    }


    // A series of component tests that test if a Bad Request exception is thrown if one or more fields in the request body is
    // blank or null. The purpose is to catch creation of invalid user objects.

    //Throw exception if username field is blank
    @Test
    void addUserShouldThrowExceptionWhenBlankUsernameField() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO(" ", "Test", "TestLastName");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"));

    }

    //Throw exception if firstName field is blank
    @Test
    void addUserShouldThrowExceptionWhenBlankFirstNameField() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "", "TestLastName");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"));

    }

    //Throw exception if lastName field is blank
    @Test
    void addUserShouldThrowExceptionWhenBlankLastNameField() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "Test", "");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"));

    }

    //Throw exception if firstName and lastName fields are blank
    @Test
    void addUserShouldThrowExceptionWhenBlankFirstNAmeAndLastNameFields() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsername", "", "");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"));

    }

    //Throw exception if all fields are blank
    @Test
    void addUserShouldThrowExceptionWhenAllFieldsBlank() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("", "", "");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"))
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());

    }

    //Throw exception if all fields are null
    @Test
    void addUserShouldThrowExceptionWhenAllFieldsNull() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO(null, null, null);

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"))
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());

    }

    //Throw exception if username is longer than 32 chars
    @Test
    void addUserShouldThrowExceptionWhenUsernameLongerThan32Chars() throws Exception {
        //Arrange
        UserCreationDTO testUserCreationDTO = new UserCreationDTO("TestUsernameTestUsernameTestUsername", "Test", "TestLastName");

        //Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUserCreationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("Username can be max 32 chars"))
                .andDo(print());

    }

}