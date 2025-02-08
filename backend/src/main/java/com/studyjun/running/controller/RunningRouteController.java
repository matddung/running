package com.studyjun.running.controller;

import com.studyjun.running.dto.running.RunningRouteRequest;
import com.studyjun.running.dto.running.RunningRouteResponse;
import com.studyjun.running.entity.RunningRoute;
import com.studyjun.running.service.RunningRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/running-route")
@RequiredArgsConstructor
public class RunningRouteController {
    private final RunningRouteService runningRouteService;

    @PostMapping("/generate")
    public ResponseEntity<RunningRouteResponse> getRunningRoute(@RequestBody RunningRouteRequest request) {
        return runningRouteService.generateRoute(request);
    }

    @GetMapping
    public ResponseEntity<List<RunningRoute>> getUserRoutes(@RequestParam String userId) {
        return runningRouteService.getUserRoutes(userId);
    }
}
