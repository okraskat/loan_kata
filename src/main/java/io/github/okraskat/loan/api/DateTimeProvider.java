package io.github.okraskat.loan.api;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
class DateTimeProvider {
    LocalTime getLocalTime() {
        return LocalTime.now();
    }
}
