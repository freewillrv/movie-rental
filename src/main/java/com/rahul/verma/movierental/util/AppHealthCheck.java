package com.rahul.verma.movierental.util;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        boolean running = true;
        Health.Builder status = Health.up();
        Map<String, String> healthStatus = new HashMap<>();
        healthStatus.put("DB", "Up");
        healthStatus.put("Elastic", "Up");

        // DO checks here. DB checks
        if (running) {
            status.status("DB and Search is up").withDetails(healthStatus).up();
        } else {
            status.down();
        }
        return status.build();
    }
}