package com.studyjun.running.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String nickname;
    private String provider;
    private String providerId;
    private String createDate;
    private String updateDate;

    @Builder
    public User(String email, String password, String nickname, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = LocalDateTime.now().toString();
        this.updateDate = LocalDateTime.now().toString();
    }
}