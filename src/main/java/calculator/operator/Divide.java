package calculator.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Divide implements Operator {

    @Override
    public char getCharacter() {
        return '/';
    }

    @Override
    public double calc(double o1, double o2) {
        return o1 / o2;
    }

    @Override
    public BigDecimal calc(BigDecimal o1, BigDecimal o2) {
        return o1.divide(o2, 10, RoundingMode.HALF_UP);
    }
}
