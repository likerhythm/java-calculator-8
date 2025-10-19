package calculator;

import java.math.BigDecimal;

public class Brain {

    public String plus(String[] input) {
        if (needBigNumber(input)) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String s : input) {
                BigDecimal v = new BigDecimal(s);
                sum = sum.add(v);
            }
            return String.valueOf(sum);
        }

        double sum = 0;
        for (String s : input) {
            sum += Double.parseDouble(s);
        }
        return String.valueOf(sum);
    }

    private boolean needBigNumber(String[] input) {
        Operands operands = new Operands(input);
        return operands.needBigNumber();
    }
}
