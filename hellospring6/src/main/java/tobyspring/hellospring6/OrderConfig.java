package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.hellospring6.data.JpaOrderRepository;
import tobyspring.hellospring6.order.OrderRepository;
import tobyspring.hellospring6.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository();
    }

    @Bean
    public OrderService orderService(final PlatformTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }
}
