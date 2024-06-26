package tobyspring.hellospring6;

public class PaymentService {
    /**
     * 1. PaymentService.prepare() 메소드
     *   - Payment 오브젝트 리턴
     */
    public Payment prepare() {
        return new Payment();
    }

    /**
     * 2. 메소드 검증
     */
    public static void main(String[] args) {
        final PaymentService paymentService = new PaymentService();
        final Payment payment = paymentService.prepare();
        System.out.println(payment);
    }
}
