package com.thinhtran.demoapi.Controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaugesController {

    private Gauge temperatureGauge;

    public GaugesController(CollectorRegistry collectorRegistry) {
        temperatureGauge = Gauge.build()
                .name("temperature")
                .help("Current temperature")
                .register(collectorRegistry);
        temperatureGauge.set(25.0);
    }

    @GetMapping(value = "/temperature/increase")
    public ResponseEntity<Double> increase() {
        temperatureGauge.inc();
        return new ResponseEntity<>(temperatureGauge.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/temperature/decrease")
    public ResponseEntity<Double> decrease() {
        temperatureGauge.dec();
        return new ResponseEntity<>(temperatureGauge.get(), HttpStatus.OK);
    }
}
