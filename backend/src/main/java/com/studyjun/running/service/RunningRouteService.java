package com.studyjun.running.service;

import com.studyjun.running.dto.running.RunningRouteRequest;
import com.studyjun.running.dto.running.RunningRouteResponse;
import com.studyjun.running.entity.RunningRoute;
import com.studyjun.running.repository.RunningRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RunningRouteService {
    private final RunningRouteRepository runningRouteRepository;

    public ResponseEntity<RunningRouteResponse> generateRoute(RunningRouteRequest request) {
        RunningRouteResponse response = new RunningRouteResponse();
        response.setRouteId("route-" + System.currentTimeMillis());
        response.setTotalDistance(request.getDistance());

        List<RunningRouteResponse.Location> waypoints = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RunningRouteResponse.Location loc = new RunningRouteResponse.Location();
            loc.setLat(request.getLatitude() + Math.random() * 0.01);
            loc.setLng(request.getLongitude() + Math.random() * 0.01);
            waypoints.add(loc);
        }
        response.setWaypoints(waypoints);

        RunningRoute runningRoute = new RunningRoute();
        runningRoute.setUserId("test123"); // TODO: 실제 사용자 ID 넣어야 함
        runningRoute.setTotalDistance(request.getDistance());

        List<RunningRoute.Location> savedWaypoints = new ArrayList<>();
        for (RunningRouteResponse.Location loc : waypoints) {
            RunningRoute.Location waypoint = new RunningRoute.Location();
            waypoint.setLat(loc.getLat());
            waypoint.setLng(loc.getLng());
            savedWaypoints.add(waypoint);
        }
        runningRoute.setWaypoints(savedWaypoints);

        runningRouteRepository.save(runningRoute);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<RunningRoute>> getUserRoutes(String userId) {
        List<RunningRoute> response = runningRouteRepository.findByUserId(userId);

        return ResponseEntity.ok(response);
    }

    public RunningRouteResponse callAIEngine(RunningRouteRequest request) {
        String aiUrl = "http://localhost:5000/generate-route";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RunningRouteResponse> response = restTemplate.postForEntity(aiUrl, request, RunningRouteResponse.class);

        return response.getBody();
    }
}
