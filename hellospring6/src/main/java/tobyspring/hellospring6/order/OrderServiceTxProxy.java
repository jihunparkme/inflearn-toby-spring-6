package tobyspring.hellospring6.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTxProxy implements OrderService {
    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(final OrderService target, final PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(final String no, final BigDecimal total) {
        return new TransactionTemplate(transactionManager).execute(status ->
                target.createOrder(no, total)
        );
    }

    @Override
    public List<Order> createOrders(final List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
                target.createOrders(reqs)
        );
    }
}
