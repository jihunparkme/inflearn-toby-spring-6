package tobyspring.hellospring6.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring6.TestPaymentCOnfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentCOnfig.class)
class PaymentServiceSpringTest {

    @Autowired private PaymentService paymentService;
    @Autowired private Clock clock;
    @Autowired private ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("원화환산금액 검증")
    void convertedAmount() throws IOException {
        // exRate: 1000
        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // exRate: 500
        exRateProviderStub.setExRate(valueOf(500));
        final Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    @DisplayName("원화환산금액 유효시간 검증")
    void validUntil() throws IOException {
        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        final LocalDateTime now = LocalDateTime.now(this.clock);
        final LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}