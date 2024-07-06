package tobyspring.hellospring6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring6.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Order order = new Order("100", BigDecimal.TEN);
        em.persist(order); // select next value for orders_SEQ

        System.out.println(order); // Order{id=1, no='100', total=10}

        em.getTransaction().commit(); // insert into orders (no,total,id) values (?,?,?)
        em.clear();
    }
}
