package com.studyjun.running.repository;

import com.studyjun.running.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {
}
