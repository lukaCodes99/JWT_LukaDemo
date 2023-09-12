package com.lukam.demosJWT.repo.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lukam.demosJWT.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    //ovaj repoziro

    /**
     * This is an example of a query
     * @param authorId
     * @param date
     * @return
     */
     
    @Query("SELECT p FROM Post p WHERE p.authorId = :authorId AND p.publishedOn > :date")
    List<Post> findCustomPosts(@Param("authorId") Integer authorId, @Param("date") LocalDateTime date);     

    Optional<Post> findByTitle(String title);

    List<Post> findByAuthorId(Integer authorId);

    List<Post> findByPublishedOnAfter(LocalDateTime date);


}
