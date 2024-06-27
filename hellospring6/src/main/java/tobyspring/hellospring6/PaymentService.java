package tobyspring.hellospring6;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    /**
     * 해외직구를 위한 원화 결제 준비 기능
     *
     * @param orderId 주문번호
     * @param currency 외국 통화 종류
     * @param foreignCurrencyAmount 외국 통화 기준 결제 금액
     * @return Payment
     */
    public Payment prepare(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        final URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final String response = br.lines().collect(Collectors.joining());
        br.close();

        final ObjectMapper mapper = new ObjectMapper();
        final ExRateData data = mapper.readValue(response, ExRateData.class);
        final BigDecimal exRate = data.rates().get("KRW");

        // 원화 환산 금액 계산
        final BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // 원화 환산 금액 유효 시간 계산
        final LocalDateTime validUtil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    }

    /**
     * 2. 메소드 검증
     */
    public static void main(String[] args) throws IOException {
        final PaymentService paymentService = new PaymentService();
        final Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
