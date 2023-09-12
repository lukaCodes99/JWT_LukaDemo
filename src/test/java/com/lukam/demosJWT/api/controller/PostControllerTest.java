package com.lukam.demosJWT.api.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukam.demosJWT.config.JwtService;
import com.lukam.demosJWT.post.Post;
import com.lukam.demosJWT.post.PostController;
import com.lukam.demosJWT.post.PostService;
import com.lukam.demosJWT.repo.post.PostRepository;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;

@EntityScan("com.lukam.demosJWT.post")
@ComponentScan(basePackages = {"com.lukam.demosJWT.repo.post"})
@WebMvcTest(controllers = PostController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {

    @MockBean //we want a "copy" of the DB
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService; 

    @Autowired
    private ObjectMapper objectMapper;

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
    public void PostController_CreatePost_ReturnsCreated() throws Exception{
        given(postRepository.save(ArgumentMatchers.any()))
                    .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response=mockMvc.perform(post("/api/v1/posts/add-post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)));
        
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        
    }



    
}
