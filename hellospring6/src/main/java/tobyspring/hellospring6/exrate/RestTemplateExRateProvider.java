package tobyspring.hellospring6.exrate;

import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring6.payment.ExRateProvider;

import java.math.BigDecimal;

public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }
}
