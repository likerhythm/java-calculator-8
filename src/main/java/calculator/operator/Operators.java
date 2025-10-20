package calculator.operator;

import java.math.BigDecimal;
import java.util.List;

public class Operators {

    private static List<Operator> operators = List.of(new Plus(), new Minus(), new Multiply(), new Divide());

    public static Operator getOperator(Class<? extends Operator> operatorClass) {
        return operators.stream().filter(o -> o.getClass().equals(operatorClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다."));
    }

    public static boolean isOperator (String target) {
        return operators.stream().anyMatch(o -> o.getCharacter() == target.charAt(0));
    }

    public static double calc(double o1, double o2, String token) {
        Operator operator = operators.stream().filter(o -> o.getCharacter() == token.charAt(0)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다."));
        return operator.calc(o1, o2);
    }

    public static BigDecimal calc(BigDecimal o1, BigDecimal o2, String token) {
        Operator operator = operators.stream().filter(o -> o.getCharacter() == token.charAt(0)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다."));
        return operator.calc(o1, o2);
    }

}
