package com.studyjun.running.service;

import com.studyjun.running.common.UserPrincipal;
import com.studyjun.running.entity.RunningRoute;
import com.studyjun.running.repository.RunningRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RunningRouteService {
    private final RunningRouteRepository runningRouteRepository;

    @Value( "${graph-hopper.key}")
    private String GRAPH_HOPPER_API_KEY;

    @Value( "${graph-hopper.url}")
    private String BASE_URL;

    public String getRoute(double startLat, double startLon, double endLat, double endLon) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("point", startLat + "," + startLon)
                .queryParam("point", endLat + "," + endLon)
                .queryParam("vehicle", "foot")
                .queryParam("locale", "ko")
                .queryParam("key", GRAPH_HOPPER_API_KEY)
                .queryParam("force", "true")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

    public ResponseEntity<List<RunningRoute>> getUserRoutes(UserPrincipal userPrincipal) {
        List<RunningRoute> response = runningRouteRepository.findByUserId(userPrincipal.getId());

        return ResponseEntity.ok(response);
    }
}
