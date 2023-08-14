package com.lukam.demosJWT.post;

import java.time.LocalDateTime;

import com.lukam.demosJWT.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data /*getters.... */
@Builder /*builder pattern, simpler */
@AllArgsConstructor
@Entity
public class Post {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String slug;
    private String content;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
