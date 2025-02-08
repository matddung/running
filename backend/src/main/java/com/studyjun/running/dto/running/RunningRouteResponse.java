package com.studyjun.running.dto.running;

import lombok.Data;

import java.util.List;

@Data
public class RunningRouteResponse {
    private String routeId;
    private double totalDistance;
    private List<Location> waypoints;

    @Data
    public static class Location {
        private double lat;
        private double lng;
    }
}