package com.studyjun.running.controller;

import com.studyjun.running.common.CurrentUser;
import com.studyjun.running.common.UserPrincipal;
import com.studyjun.running.entity.RunningRoute;
import com.studyjun.running.service.RunningRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/running-route")
@RequiredArgsConstructor
public class RunningRouteController {
    private final RunningRouteService runningRouteService;

    @PostMapping("/get-route")
    public String getRoute(double startLat, double startLon, double endLat, double endLon) {
        return runningRouteService.getRoute(startLat, startLon, endLat, endLon);
    }

    @GetMapping
    public ResponseEntity<List<RunningRoute>> getUserRoutes(@CurrentUser UserPrincipal userPrincipal) {
        return runningRouteService.getUserRoutes(userPrincipal);
    }
}
