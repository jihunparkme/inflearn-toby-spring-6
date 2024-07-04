package tobyspring.hellospring6.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring6.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(final String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = executeApi(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return parseExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal parseExRate(final String response) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }

    private static String executeApi(final URI uri) throws IOException {
        String response;
        final HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }
}
