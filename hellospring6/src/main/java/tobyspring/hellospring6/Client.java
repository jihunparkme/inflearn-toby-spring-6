package tobyspring.hellospring6;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring6.payment.Payment;
import tobyspring.hellospring6.payment.PaymentService;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        final PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment1 : " + payment1);
    }
}
