package com.thinhtran.demoapi.Controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaitController {

    private Histogram histogram;

    public WaitController(CollectorRegistry collectorRegistry) {
        this.histogram = Histogram.build()
                .name("request_duration")
                .help("Duration time of HTTP request")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/wait")
    public ResponseEntity<String> httpDuration() throws InterruptedException {
        Histogram.Timer timer = histogram.startTimer();
        long duration = Double.valueOf(Math.random() * 10.0 * 1000.0).longValue();
        Thread.sleep(duration);
        timer.observeDuration();
        return new ResponseEntity<>("Wait in " + duration + " (ms)", HttpStatus.OK);
    }

}
