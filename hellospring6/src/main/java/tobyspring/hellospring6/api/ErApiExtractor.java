package tobyspring.hellospring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring6.exrate.ExRateData;

import java.math.BigDecimal;

public class ErApiExtractor implements ExRateExtractor {
    @Override
    public BigDecimal extract(final String response) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
