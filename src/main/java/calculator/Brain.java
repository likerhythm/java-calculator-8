package calculator;

import calculator.operator.Operators;
import java.math.BigDecimal;
import java.util.Stack;

public class Brain {

    public String run(final PostFix postfix) {
        if (postfix.getNeedBigNumber()) {
            return calculateBigDecimal(postfix.getValue()).toString();
        }

        return String.valueOf(calculate(postfix.getValue()));
    }

    private double calculate(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            if (Operators.isOperator(token)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                double result = 0;
                result = Operators.calc(operand1, operand2, token);

                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private BigDecimal calculateBigDecimal(String postfix) {
        Stack<BigDecimal> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            if (Operators.isOperator(token)) {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                BigDecimal result = Operators.calc(operand1, operand2, token);
                stack.push(result);
            }
            else {
                stack.push(new BigDecimal(token));
            }
        }

        return stack.pop();
    }
}
