package com.example.javaspringjpaexam;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/{userId}/posts/{channelID}")
    public ResponseEntity<PostDTO> createPostOnUserId(@RequestBody @Valid Post newPost,
                                                   @PathVariable long userId,
                                                   @PathVariable long channelID) {
        PostDTO post = userService.createPostOnUserId(newPost, userId, channelID);
        if ( post != null){
            return ResponseEntity.ok(post);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Read
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        UserDTO user = userService.getUserById(id);
        if ( user != null){
            return ResponseEntity.ok(user);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserDTO>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getUsersPosts(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUsersPosts(userId));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById (@RequestBody @Valid User userToUpdate, @PathVariable long id) {
        UserDTO user = userService.updateUserById(userToUpdate, id);
        if ( user != null){
            return ResponseEntity.ok(user);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable  long id) throws BadRequestException {
        userService.deleteUser(id);
    }


}
