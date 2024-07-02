package tobyspring.hellospring6.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {

    @Test
    @DisplayName("Clock 으로 현재 시간 가져오기")
    void clock() {
        final Clock clock = Clock.systemDefaultZone();

        final LocalDateTime dt1 = LocalDateTime.now(clock);
        final LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isAfter(dt1);
    }

    @Test
    @DisplayName("Clock 으로 고정된 시간 가져오기")
    void fixedClock() {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        final LocalDateTime dt1 = LocalDateTime.now(clock);
        final LocalDateTime dt2 = LocalDateTime.now(clock);
        final LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(dt2).isEqualTo(dt1);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }
}
