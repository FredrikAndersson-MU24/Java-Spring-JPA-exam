package com.example.javaspringjpaexam.controller;

import com.example.javaspringjpaexam.dto.UserCreationDTO;
import com.example.javaspringjpaexam.dto.UserDetailedDTO;
import com.example.javaspringjpaexam.dto.UserMinimalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

// This is an integration test, testing the whole way from the controller layer to the database.
// To isolate the test data from production data, these tests use the application-test.properties configuration.

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    // POST a new user to the database and verify that it has been saved in the database by using a GET command to
    // retrieve it.
    @Test
    public void testSaveAndGetUserFromDatabase() {

        //Arrange
        UserCreationDTO newUser = new UserCreationDTO("TestUsername", "TestFirstName", "TestLastName");

        //Act
        ResponseEntity<UserMinimalDTO> postResponse = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/users", newUser,
                UserMinimalDTO.class);
        Long userId = postResponse.getBody().getId();
        ResponseEntity<UserDetailedDTO> getResponse = testRestTemplate.getForEntity("http://localhost:" + port + "/users/" + userId, UserDetailedDTO.class);

        //Assert
        assertEquals(HttpStatusCode.valueOf(200), postResponse.getStatusCode()); // Verify Status 200 for POST request
        assertEquals(HttpStatusCode.valueOf(200), getResponse.getStatusCode()); // Verify Status 200 for GET request
        assertEquals(userId, getResponse.getBody().getId()); // Verify that the User ID matches for both requests
        assertEquals(newUser.getUsername(), getResponse.getBody().getUsername()); // Verify that the usernames match
    }


    // POST a new user to the database, DELETE the user, verify both requests
    @Test
    public void testSaveAndDeleteUserFromDatabase() {

        //Arrange
        UserCreationDTO newUser = new UserCreationDTO("TestUsername2", "TestFirstName", "TestLastName");

        //Act & Assert
        ResponseEntity<UserMinimalDTO> postResponse = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/users", newUser,
                UserMinimalDTO.class);
        Long userId = postResponse.getBody().getId();
        assertTrue(postResponse.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))); // Verify Status 200
        assertEquals(testRestTemplate
                .getForEntity("http://localhost:" + port + "/users/" + userId, UserDetailedDTO.class)
                .getBody()
                .getUsername(), newUser.getUsername()); // Verify that the username is correct

        testRestTemplate.delete("http://localhost:" + port + "/users/" + userId);
        assertTrue(testRestTemplate.getForEntity("http://localhost:" + port + "/users/" + userId, UserDetailedDTO.class).getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(404)));

    }

}