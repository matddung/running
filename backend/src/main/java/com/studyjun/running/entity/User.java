package com.studyjun.running.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user")
public class User extends BaseEntity{
    @Indexed(unique = true)
    private String email;

    private String password;
    private String nickname;
    private String provider;
    private String providerId;

    @Builder
    public User(String email, String password, String nickname, String provider, String providerId) {
        super();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
    }
}