package tobyspring.hellospring6;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(final String currency) throws IOException {
        final URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final String response = br.lines().collect(Collectors.joining());
        br.close();

        final ObjectMapper mapper = new ObjectMapper();
        final ExRateData data = mapper.readValue(response, ExRateData.class);

        System.out.println("API ExRate: " + data.rates().get("KRW"));

        return data.rates().get("KRW");
    }
}
