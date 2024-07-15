package tobyspring.hellospring6.order;

import java.math.BigDecimal;

public class Order {
    private Long id;

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

    public void setId(final Long id) {
        this.id = id;
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
