package tobyspring.hellospring6;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        final PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        final Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
