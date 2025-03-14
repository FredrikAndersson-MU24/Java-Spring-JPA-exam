package com.example.javaspringjpaexam.controller;

import com.example.javaspringjpaexam.dto.PostCreationDTO;
import com.example.javaspringjpaexam.dto.PostDetailedDTO;
import com.example.javaspringjpaexam.dto.PostMinimalDTO;
import com.example.javaspringjpaexam.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //Create
    // In UserService since I decided it to be the most logical decision, it's a user entity that creates a post.

    //Read
    @GetMapping("/details")
    public ResponseEntity<List<PostDetailedDTO>> getAllPostDetailedDTO() {
        List<PostDetailedDTO> posts = postService.getAllPostDetailedDTO();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PostMinimalDTO>> getAllPostMinimalDTO() {
        List<PostMinimalDTO> posts = postService.getAllPostMinimalDTO();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<PostDetailedDTO> updatePostOnPostId(@RequestBody @Valid PostCreationDTO updatedPost, @PathVariable long id) {
        return ResponseEntity.ok(postService.updatePostOnPostId(updatedPost, id));
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
    }

}
