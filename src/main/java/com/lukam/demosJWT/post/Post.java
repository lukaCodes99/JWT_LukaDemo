package com.lukam.demosJWT.post;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data /*getters.... */
@Builder /*builder pattern, simpler */
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Post {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String slug;
    private String content;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;
    private Integer authorId;
    
}
