package com.studyjun.running.repository;

import com.studyjun.running.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
