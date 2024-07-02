package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring6.exrate.CachedExRateProvider;
import tobyspring.hellospring6.payment.ExRateProvider;
import tobyspring.hellospring6.exrate.WebApiExRateProvider;
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
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
