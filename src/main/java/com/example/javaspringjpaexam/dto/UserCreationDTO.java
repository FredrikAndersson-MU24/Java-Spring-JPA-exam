package com.example.javaspringjpaexam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreationDTO {

    @NotBlank(message = "must not be blank or null")
    @Size(max = 32, message ="Username can be max 32 chars")
    private String username;
    @NotBlank(message = "must not be blank or null")
    @Size(max = 32, message ="firstName can be max 32 chars")
    private String firstName;
    @NotBlank(message = "must not be blank or null")
    @Size(max = 32, message ="lastName can be max 32 chars")
    private String lastName;

    public UserCreationDTO(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
