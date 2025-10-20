package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class Brain {

    public String plus(final PostFix postfix) {
        if (postfix.getNeedBigNumber()) {
            return calculateBigDecimal(postfix.getValue()).toString();
        }

        return String.valueOf(calculate(postfix.getValue()));
    }

    public static double calculate(String postfix) {
        Stack<Double> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                sb.append(c);
                if (i + 1 >= postfix.length()) {
                    stack.push(Double.parseDouble(sb.toString()));
                }
            } else if(c == ' ') {
                stack.push(Double.parseDouble(sb.toString()));
                sb = new StringBuilder();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                double result = 0;
                if (c == '+') {
                    result = operand1 + operand2;
                } else if (c == '-') {
                    result = operand1 - operand2;
                } else if (c == '*') {
                    result = operand1 * operand2;
                } else if (c == '/') {
                    result = operand1 / operand2;
                }

                stack.push(result);
            }
        }

        return stack.pop();
    }

    public static BigDecimal calculateBigDecimal(String postfix) {
        Stack<BigDecimal> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            if (token.equals("+") || token.equals("-") ||
                    token.equals("*") || token.equals("/")) {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();

                BigDecimal result = null;
                switch (token) {
                    case "+":
                        result = operand1.add(operand2);
                        break;
                    case "-":
                        result = operand1.subtract(operand2);
                        break;
                    case "*":
                        result = operand1.multiply(operand2);
                        break;
                    case "/":
                        result = operand1.divide(operand2, 10, RoundingMode.HALF_UP);
                        break;
                }

                stack.push(result);
            }
            else {
                stack.push(new BigDecimal(token));
            }
        }

        return stack.pop();
    }
}
