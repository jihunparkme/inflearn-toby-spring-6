package tobyspring.hellospring6.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import tobyspring.hellospring6.api.ApiExecutor;
import tobyspring.hellospring6.api.ErApiExRateExtractor;
import tobyspring.hellospring6.api.ExRateExtractor;
import tobyspring.hellospring6.api.SimpleApiExecutor;
import tobyspring.hellospring6.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(final String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private static BigDecimal runApiForExRate(final String url, final ApiExecutor apiExecutor, final ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
