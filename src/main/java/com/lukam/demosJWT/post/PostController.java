package com.lukam.demosJWT.post;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lukam.demosJWT.repo.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository posts;

    @GetMapping("")
    public Iterable<Post> findAll(){
        return posts.findAll();
    }
    
    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        return posts.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
    }
    

    @PostMapping("/add-post")
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        Post savedPost = posts.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).body("Post created successfully");
    }
    

}
