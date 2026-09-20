package com.elpsykongrood.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class DateTimeUtils {

    private final ZoneId displayZone;

    public DateTimeUtils(@Value("${app.display-zone}") ZoneId displayZone) {
        this.displayZone = displayZone;
    }

    public int currentYear() {
        return Instant.now().atZone(displayZone).getYear();
    }

    public String format(Instant instant, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withZone(displayZone).format(instant);
    }
}
