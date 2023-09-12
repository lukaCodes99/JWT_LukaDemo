package com.lukam.demosJWT.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lukam.demosJWT.post.Post;
import com.lukam.demosJWT.post.PostService;
import com.lukam.demosJWT.repo.post.PostRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@EntityScan("com.lukam.demosJWT.post")
@EnableJpaRepositories(basePackages = {"com.lukam.demosJWT.repo.post"})
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock //we want a "copy" of the DB
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post;

    @BeforeEach
    public void init(){
        post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();
    }

    @Test
    public void PostService_CreatePost_ReturnsPost(){

        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
 
        Post savedPost=postService.createPost(post);

        Assertions.assertThat(savedPost).isNotNull();

    }

    @Test
    public void PostService_UpdatePost_ReturnsPost(){
        
        when(postRepository.findById((long) 1)).thenReturn(Optional.ofNullable(post));

        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        Post savedPost = postService.updatePost(post, (long) 1);

        Assertions.assertThat(savedPost).isNotNull();

    }

    @Test
    public void PostService_FindById_ReturnPost() {
        Long postId =(long) 1;
        
        
        when(postRepository.findById(postId)).thenReturn(Optional.ofNullable(post));

        Post postReturn = postService.getPostById(postId);

        Assertions.assertThat(postReturn).isNotNull();
    }

    @Test
    public void PostService_DeleteById_ReturnPost() {
        Long postId =(long) 1;
        
        
        when(postRepository.findById(postId)).thenReturn(Optional.ofNullable(post));

        assertAll(() -> postService.deletePostById(postId));
    }



    
}
