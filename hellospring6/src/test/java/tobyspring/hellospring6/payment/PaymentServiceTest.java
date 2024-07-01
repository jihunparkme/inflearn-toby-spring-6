package tobyspring.hellospring6.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1_000), valueOf(10_000));
        testAmount(valueOf(3_000), valueOf(30_000));

        // 원화환산금액 유효시간 검증
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(final BigDecimal exRate, final BigDecimal convertedAmount) throws IOException {
        final PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보 검증
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 원화환산금액 검증
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}