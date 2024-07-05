package tobyspring.hellospring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    /**
     * Default Callback
     */
    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExtractor();
    }

    public ApiTemplate(final ApiExecutor apiExecutor, final ExRateExtractor exRateExtractor) {
        this.apiExecutor = apiExecutor;
        this.exRateExtractor = exRateExtractor;
    }

    public BigDecimal getExRate(final String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ExRateExtractor exRateExtractor) {
        return this.getExRate(url, this.apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(final String url, final ApiExecutor apiExecutor, final ExRateExtractor exRateExtractor) {
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
