package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring6.exrate.CachedExRateProvider;
import tobyspring.hellospring6.payment.ExRateProvider;
import tobyspring.hellospring6.exrate.WebApiExRateProvider;
import tobyspring.hellospring6.payment.PaymentService;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
