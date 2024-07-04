package tobyspring.hellospring6.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
    private Long orderId; // 주문번호
    private String currency; // 외국 통화 종류
    private BigDecimal foreignCurrencyAmount; // 외국 통화 기준 결제 금액

    private BigDecimal exRate; // 적용 환율
    private BigDecimal convertedAmount; // 원화 환산 금액
    private LocalDateTime validUntil; // 원화 환산 금액 유효시간

    public Payment(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount, final BigDecimal exRate, final BigDecimal convertedAmount, final LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount, final ExRateProvider exRateProvider, final Clock clock) {
        final BigDecimal exRate = exRateProvider.getExRate(currency);
        final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        final LocalDateTime validUtil = LocalDateTime.now(clock).plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    }

    public boolean isValid(final Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
