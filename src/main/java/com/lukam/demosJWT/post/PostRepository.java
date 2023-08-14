package com.lukam.demosJWT.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>{

    //ovaj repoziro

    /**
     * This is an example of a query
     * @param authorUsername
     * @param date
     * @return
     */
    @Query("SELECT p FROM Post p WHERE p.author = :authorUsername AND p.publishedOn > :date")
    List<Post> findCustomPosts(@Param("authorUsername") String authorUsername, @Param("date") LocalDateTime date);

    

}
