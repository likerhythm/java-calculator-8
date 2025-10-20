package calculator.operator;

import java.util.List;

public class Operators {

    private static List<Operator> operators = List.of(new Plus(), new Minus(), new Multiply(), new Divide());

    public static Operator getOperator(Class<? extends Operator> operatorClass) {
        return operators.stream().filter(o -> o.getClass().equals(operatorClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다."));
    }
}
