package com.lukam.demosJWT.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukam.demosJWT.repo.post.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {
    
    @Autowired
    private final PostRepository postRepository;

    
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> findByAuthorId(Integer authorId) {
        return postRepository.findByAuthorId(authorId);
    }
 
    public List<Post> findByPublishedOnAfter(LocalDateTime date) {
        return postRepository.findByPublishedOnAfter(date);
    }

    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        return post;
    }

    public Post updatePost(Post newPost, Long id){
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // Update the fields of the existing post with values from the newPost
        post.setTitle(newPost.getTitle());
        post.setSlug(newPost.getSlug());
        post.setContent(newPost.getContent());
        post.setPublishedOn(newPost.getPublishedOn());
        post.setUpdatedOn(LocalDateTime.now()); // Update the updatedOn timestamp if needed
        post.setAuthorId(newPost.getAuthorId());

        // Save the updated post back to the database
        Post updatedPost = postRepository.save(post);

        return updatedPost;
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        postRepository.delete(post);
    }

}
