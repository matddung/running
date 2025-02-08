package com.studyjun.running.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "running_routes")
public class RunningRoute extends BaseEntity{
    private String userId;
    private double totalDistance;
    private List<Location> waypoints;

    @Data
    public static class Location {
        private double lat;
        private double lng;
    }
}