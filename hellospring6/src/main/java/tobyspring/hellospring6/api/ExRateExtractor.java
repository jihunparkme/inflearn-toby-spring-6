package tobyspring.hellospring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(final String response) throws JsonProcessingException;
}
