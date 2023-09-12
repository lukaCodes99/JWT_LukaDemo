package com.lukam.demosJWT.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import com.lukam.demosJWT.post.Post;
import com.lukam.demosJWT.repo.post.PostRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes={PostRepositoryTests.class})
@EntityScan("com.lukam.demosJWT.post")
@EnableJpaRepositories(basePackages = {"com.lukam.demosJWT.repo.post"})
public class PostRepositoryTests {
    
    @Autowired
    private PostRepository postRepository;

    @Test
    public void PostRepository_SaveAll_ReturnSavedPosts(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();

        //Act
        Post savedPost = postRepository.save(post);

        //Assert
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);

    }

    @Test
    public void PostRepository_GetAll_ReturnMoreThanOnePost(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();
        Post post2 = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();

        //Act
        postRepository.save(post);
        postRepository.save(post2);

        List<Post> postList= postRepository.findAll();

        //Assert
        Assertions.assertThat(postList).isNotNull();
        Assertions.assertThat(postList.size()).isEqualTo(2);

    }

    @Test
    public void PostRepository_GetById_ReturnFoundPost(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();
        

        //Act
        postRepository.save(post);

        Post postSaved= postRepository.findById(post.getId()).get();

        //Assert
        Assertions.assertThat(postSaved).isNotNull();

    }

    @Test
    public void PostRepository_FindByTitle_ReturnPostNotNull(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();
        

        //Act
        postRepository.save(post);

        Post postSaved = postRepository.findByTitle(post.getTitle()).get();

        //Assert
        Assertions.assertThat(postSaved).isNotNull();

    }

    @Test
    public void PostRepository_UpdatePost_ReturnPostNotNull(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1)  
            .build();
        

        //Act
        postRepository.save(post);
        Post postSaved= postRepository.findById(post.getId()).get();

        postSaved.setTitle("New title");
        
        Post updatedPost = postRepository.save(postSaved);

        //Assert
        Assertions.assertThat(updatedPost.getTitle()).isNotNull();

    }

    @Test
    public void PostRepository_DeletePost_ReturnPostIsEmpty(){

        //Arrange
        Post post = Post.builder()
            .title("Sample Title")
            .slug("sample-title")
            .content("This is some sample content for the post.")
            .publishedOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .authorId(1) 
            .build();
        

        //Act
        postRepository.save(post);
        Post postSaved= postRepository.findById(post.getId()).get();

        postRepository.deleteById(postSaved.getId());

        Optional<Post> postReturn = postRepository.findById(post.getId());
        

        //Assert
        Assertions.assertThat(postReturn).isEmpty();

    }


}
