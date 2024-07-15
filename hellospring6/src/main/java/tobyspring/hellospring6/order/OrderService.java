package tobyspring.hellospring6.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(final OrderRepository orderRepository, final PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        final Order order = new Order(no, total);

        this.orderRepository.save(order); // setAutoCommit(true)
        return order;
    }

    /**
     * 메서드가 하나의 트랜잭션으로 돌아가야 한다면,
     * 어쩔 수 없이 트랜잭션 기능을 담당하는 기술적인 클래스나 인터페이스(TransactionTemplate)가 등장해야 하나?
     */
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
            reqs.stream().map(req -> createOrder(req.no(), req.total())).toList()
        );
    }
}
