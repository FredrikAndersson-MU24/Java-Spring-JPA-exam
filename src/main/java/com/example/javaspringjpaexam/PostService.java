package com.example.javaspringjpaexam;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //Create
    // In UserService since I decided it was the most logical decision, it's a user entity that creates a post.

    //Read
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<PostDTO> getAllPostDTO() {
        List<Post> posts = getAllPosts();
        return posts.stream().map(PostMapper.INSTANCE::postToPostDTO).collect(Collectors.toList());
    }

    //Update
    public Post updatePostOnPostId(Post updatedPost, long id) {
        return postRepository.findById(id).map(p -> {
            p.setTitle(updatedPost.getTitle());
            p.setBody(updatedPost.getBody());
            p.setEdited(LocalDate.now());
            return postRepository.save(p);
        }).orElse(null);
    }

    //Delete
    public boolean deletePostById(long id) {
        boolean exists = postRepository.existsById(id);
        if (exists) {
            postRepository.deleteById(id);
        }
        return exists;
    }

}
