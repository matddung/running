package com.studyjun.running.dto.running;

import lombok.Data;

@Data
public class RunningRouteRequest {
    private double latitude;
    private double longitude;
    private double distance;
    private String difficulty;
}
