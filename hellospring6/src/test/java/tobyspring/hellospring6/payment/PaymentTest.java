package tobyspring.hellospring6.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {
    @Test
    void createPrepared() throws IOException {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        final Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock
        );

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() throws IOException {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        final Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock
        );

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }
}