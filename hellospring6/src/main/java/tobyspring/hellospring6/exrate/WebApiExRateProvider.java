package tobyspring.hellospring6.exrate;

import tobyspring.hellospring6.api.ApiTemplate;
import tobyspring.hellospring6.api.ErApiExRateExtractor;
import tobyspring.hellospring6.api.HttpClientApiExecutor;
import tobyspring.hellospring6.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    private ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(final String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
