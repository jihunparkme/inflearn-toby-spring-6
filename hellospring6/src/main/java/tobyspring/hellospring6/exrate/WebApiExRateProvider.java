package tobyspring.hellospring6.exrate;

import tobyspring.hellospring6.api.ApiTemplate;
import tobyspring.hellospring6.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    /**
     * Template Bean
     */
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(final ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url);
    }
}
