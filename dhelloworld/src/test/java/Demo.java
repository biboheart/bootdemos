import org.junit.jupiter.api.Test;

public class Demo {
    @Test
    public void tttt() {
        Integer i = null;
        try {
            i = Integer.parseInt("平车");
        } catch (NumberFormatException e) {
            i = 0;
        }
        System.out.println(i);
    }
}
