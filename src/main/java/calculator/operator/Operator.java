package calculator.operator;

import java.math.BigDecimal;

public interface Operator {
    char getCharacter();
    double calc(double o1, double o2);
    BigDecimal calc(BigDecimal o1, BigDecimal o2);
}
