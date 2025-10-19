package calculator;

import java.math.BigDecimal;

public class Brain {
    public String plus(String[] input) {
        BigDecimal sum = BigDecimal.ZERO;
        for (String s : input) {
            BigDecimal v = new BigDecimal(s);
            sum = sum.add(v);
        }
        return String.valueOf(sum);
    }
}
