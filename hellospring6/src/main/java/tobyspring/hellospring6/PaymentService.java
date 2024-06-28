package tobyspring.hellospring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    /**
     * 해외직구를 위한 원화 결제 준비 기능
     *
     * @param orderId               주문번호
     * @param currency              외국 통화 종류
     * @param foreignCurrencyAmount 외국 통화 기준 결제 금액
     * @return Payment
     */
    public Payment prepare(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount) throws IOException {
        final BigDecimal exRate = exRateProvider.getExRate(currency);
        final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        final LocalDateTime validUtil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    }
}
