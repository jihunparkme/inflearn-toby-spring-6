package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring6.exrate.CachedExRateProvider;
import tobyspring.hellospring6.exrate.RestTemplateExRateProvider;
import tobyspring.hellospring6.payment.ExRateProvider;
import tobyspring.hellospring6.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public RestTemplate restTemplate() {
        /**
         * SimpleClientHttpRequestFactory is default factory. (ref. HttpAccessor)
         */
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
