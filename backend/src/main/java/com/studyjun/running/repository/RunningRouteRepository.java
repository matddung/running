package com.studyjun.running.repository;

import com.studyjun.running.entity.RunningRoute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RunningRouteRepository extends MongoRepository<RunningRoute, String> {
    List<RunningRoute> findByUserId(String userId);
}
