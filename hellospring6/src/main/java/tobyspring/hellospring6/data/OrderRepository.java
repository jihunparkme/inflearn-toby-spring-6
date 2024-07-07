package tobyspring.hellospring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring6.order.Order;

public class OrderRepository {
    /**
     * @PersistenceContext 표준 애노테이션으로 JPA가 적절한 엔티티 매니저를 주입
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }
}
