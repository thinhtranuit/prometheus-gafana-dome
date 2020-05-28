package com.thinhtran.demoapi;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Counter userRequestedCount;

    public UserService(MeterRegistry meterRegistry) {
        userRequestedCount = Counter.builder("custom.user.requested")
                .register(meterRegistry);
    }


    public User getUserById(int id) {
        userRequestedCount.increment(1.0);
        return new User(id, "Thinh", 24);
    }
}
