package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.PostDetailedDTO;
import com.example.javaspringjpaexam.dto.PostMinimalDTO;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.mapper.PostMapper;
import com.example.javaspringjpaexam.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //Create
    // In UserService since I decided it was the most logical decision, it's a user entity that creates a post.

    //Read
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<PostMinimalDTO> getAllPostMinimalDTO() {
        List<Post> posts = getAllPosts();
        return posts.stream().map(PostMapper.INSTANCE::postToPostMinimalDTO).collect(Collectors.toList());
    }

    public List<PostDetailedDTO> getAllPostDetailedDTO() {
        List<Post> posts = getAllPosts();
        return posts.stream().map(PostMapper.INSTANCE::postToPostDetailedDTO).collect(Collectors.toList());
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
