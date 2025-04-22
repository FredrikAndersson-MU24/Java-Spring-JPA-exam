package com.example.javaspringjpaexam.controller;

import com.example.javaspringjpaexam.dto.UserCreationDTO;
import com.example.javaspringjpaexam.dto.UserMinimalDTO;

import com.example.javaspringjpaexam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    // Component tests for the addUser() method in the UserController. addUser() is called when making a POST request
    // on the "/users" end-point.


    // A series of component tests that tests if using a POST request to add a valid UserCreationDTO returns status code 200 and the created
    // user as a UserMinimalDTO object.
    @Test
    void addUserShouldReturnResponseWithUserMinimalDTOAndStatusOkWhenPostingValidUserCreationDTOHttpRequest() throws Exception {

        //Arrange
        when(userService.addUser(any())).thenReturn(new UserMinimalDTO(1L, "TestUsername"));

        //Act & Assert
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsername", "Test", "TestLastName"))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new UserMinimalDTO(1L, "TestUsername"))))
                .andDo(print());

    }


    // Test with a username of the maximum allowed number of characters
    @Test
    void addUserShouldReturnResponseWithUserMinimalDTOAndStatusOkWhenPostingUserCreationDTOHttpRequestWithUsernameOfMaxAllowedLength32Chars() throws Exception {
        //Arrange
        when(userService.addUser(any())).thenReturn(new UserMinimalDTO(1L, "TestUsernameTestUsernameTestUser"));

        //Act & Assert
        // Test with all fields null
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsernameTestUsernameTestUser", "Test", "TestLastName"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("TestUsernameTestUsernameTestUser"))
                .andDo(print());

    }



    // A series of component tests that test if a Bad Request exception is thrown if one or more fields in the request body is
    // blank or null. The purpose is to catch creation of invalid user objects.

    //Throw exception if username field is blank
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankUsernameField() throws Exception {

        //Act & Assert
        // Test with username blank
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO(" ", "Test", "TestLastName"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"))
                .andDo(print());

    }

    //Throw exception if firstName field is blank
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankFirstNameField() throws Exception {

        //Act & Assert
        //Test with firstName blank
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsername", "", "TestLastName"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andDo(print());

    }

    //Throw exception if lastName field is blank
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankLastNameField() throws Exception {

        //Act & Assert
        // Test with lastName blank
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsername", "Test", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());

    }

    //Throw exception if firstName and lastName fields are blank
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankFirstNAmeAndLastNameFields() throws Exception {

        //Act & Assert
        // Test with firstName and lastName blank
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsername", "", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());
    }

    //Throw exception if all fields are blank
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithAllFieldsBlank() throws Exception {

        //Act & Assert

        // Test with all fields blank
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO("", "", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"))
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());


    }

    //Throw exception if all fields are null
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithAllFieldsNull() throws Exception {

        //Act & Assert
        // Test with all fields null
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( new UserCreationDTO(null, null, null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("must not be blank or null"))
                .andExpect(jsonPath("$.firstName").value("must not be blank or null"))
                .andExpect(jsonPath("$.lastName").value("must not be blank or null"))
                .andDo(print());

    }


    //Throw exception if username is longer than 32 chars
    @Test
    void addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithUsernameLongerThan32Chars() throws Exception {

        //Act & Assert
        // Test with all fields null
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( new UserCreationDTO("TestUsernameTestUsernameTestUsername", "Test", "TestLastName"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("Username can be max 32 chars"))
                .andDo(print());

    }

}