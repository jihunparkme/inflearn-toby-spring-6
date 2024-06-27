package tobyspring.hellospring6;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    /**
     * 해외직구를 위한 원화 결제 준비 기능
     *
     * 주문번호, 외국 통화 종류, 외국 통화 기준 결제 금액을 전달 받아서 다음의 정보를 더해 Payment를 생성한다
     * - 적용 환율
     * - 원화 환산 금액
     * - 원화 환산 금액 유효시간
     *
     * @param orderId
     * @param currency
     * @param foreignCurrencyAmount
     * @return
     */
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        // TODO: 환율 가져오기
        // TODO: 금액 계산
        // TODO: 유효 시간 계산
        return new Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());
    }

    /**
     * 2. 메소드 검증
     */
    public static void main(String[] args) {
        final PaymentService paymentService = new PaymentService();
        final Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
