import org.junit.Test;

import java.math.BigDecimal;

public class Demo {
    @Test
    public void tttt() {
        BigDecimal num = new BigDecimal("-0.5").stripTrailingZeros();
        BigDecimal onum = new BigDecimal("-0.5").stripTrailingZeros();
        onum = onum.add(num);
        System.out.println(onum.doubleValue());
        System.out.println(onum);
        num = num.subtract(new BigDecimal("-0.5"));
        System.out.println(num.doubleValue());
        Long payment = 150000L;
        BigDecimal discount = new BigDecimal(payment).multiply(new BigDecimal("0.8"));
        System.out.println(discount.longValue());
    }
}
