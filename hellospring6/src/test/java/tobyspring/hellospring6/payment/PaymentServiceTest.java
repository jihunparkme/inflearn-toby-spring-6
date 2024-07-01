package tobyspring.hellospring6.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring6.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        final PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        final Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율 정보 검증
        assertThat(payment.getExRate()).isNotNull();

        // 원화환산금액 검증
        assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액 유효시간 검증
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}