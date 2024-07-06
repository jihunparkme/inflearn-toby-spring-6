package tobyspring.hellospring6.order;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String no;

    private BigDecimal total;

    /**
     * JPA 에서는 파라미터가 있는 생성자를 만들 경우 파라미터가 없는 기본 생성자도 생성해 주어야 한다.
     */
    public Order() {
    }

    public Order(final String no, final BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}
