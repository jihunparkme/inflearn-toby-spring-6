package tobyspring.hellospring6.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    private Clock clock;

    @BeforeEach
    void beforeEach() {
         this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("원화환산금액 검증")
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1_000), valueOf(10_000));
        testAmount(valueOf(3_000), valueOf(30_000));
    }

    @Test
    @DisplayName("원화환산금액 유효시간 검증")
    void validUntil() {
        final PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), this.clock);

        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        final LocalDateTime now = LocalDateTime.now(this.clock);
        final LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private void testAmount(final BigDecimal exRate, final BigDecimal convertedAmount) {
        final PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), this.clock);
        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보 검증
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 원화환산금액 검증
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}