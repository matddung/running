package com.studyjun.running.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "post")
public class Post extends BaseEntity{
    private String userId;
    private String content;

    @Builder
    public Post(String userId, String content) {
        super();
        this.userId = userId;
        this.content = content;
    }
}