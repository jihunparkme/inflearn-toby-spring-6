package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring6.exrate.CachedExRateProvider;
import tobyspring.hellospring6.exrate.WebApiExRateProvider;
import tobyspring.hellospring6.payment.ExRateProvider;
import tobyspring.hellospring6.payment.ExRateProviderStub;
import tobyspring.hellospring6.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
