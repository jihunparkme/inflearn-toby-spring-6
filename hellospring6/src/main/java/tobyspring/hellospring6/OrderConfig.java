package tobyspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.hellospring6.data.JdbcOrderRepository;
import tobyspring.hellospring6.order.OrderRepository;
import tobyspring.hellospring6.order.OrderService;
import tobyspring.hellospring6.order.OrderServiceImpl;
import tobyspring.hellospring6.order.OrderServiceTxProxy;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(
            final PlatformTransactionManager transactionManager,
            final OrderRepository orderRepository
    ) {
        return new OrderServiceTxProxy(
                new OrderServiceImpl(orderRepository),
                transactionManager
        );
    }
}
