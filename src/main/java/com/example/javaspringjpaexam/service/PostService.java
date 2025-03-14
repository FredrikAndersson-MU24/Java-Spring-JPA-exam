package com.example.javaspringjpaexam.service;

import com.example.javaspringjpaexam.dto.*;
import com.example.javaspringjpaexam.entity.Post;
import com.example.javaspringjpaexam.mapper.PostMapper;
import com.example.javaspringjpaexam.repository.PostRepository;
import org.springframework.dao.DuplicateKeyException;
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
    public PostDetailedDTO updatePostOnPostId(PostCreationDTO updatedPost, long id) {
        boolean titleExists = postRepository.existsByTitleIgnoreCase(updatedPost.getTitle());
        if (!titleExists) {
            Post post = postRepository.findById(id).map(p -> {
                p.setTitle(updatedPost.getTitle());
                p.setBody(updatedPost.getBody());
                p.setEdited(LocalDate.now());
                return postRepository.save(p);
            }).orElse(null);
            return PostMapper.INSTANCE.postToPostDetailedDTO(post);
        } else throw new DuplicateKeyException("Title already exists");
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
