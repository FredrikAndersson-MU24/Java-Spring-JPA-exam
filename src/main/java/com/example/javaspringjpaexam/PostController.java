package com.example.javaspringjpaexam;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //Create
    // In UserService since I decided it to be the most logical decision, it's a user entity that creates a post.

    //Read
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostOnPostId(@RequestBody @Valid Post updatedPost, @PathVariable long id) {
        return ResponseEntity.ok(postService.updatePostOnPostId(updatedPost, id));
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable long id) {
        boolean exists = postService.deletePostById(id);
        if ( exists) {
            return ResponseEntity.ok("Deleted!");
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
