package tobyspring.hellospring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring6.order.Order;
import tobyspring.hellospring6.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    /**
     * @PersistenceContext 표준 애노테이션으로 JPA가 적절한 엔티티 매니저를 주입
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }
}
