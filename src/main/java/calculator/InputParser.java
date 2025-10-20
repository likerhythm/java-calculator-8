package calculator;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern CUSTOM_PREFIX = Pattern.compile("^//(.*)\\\\n");
    private final Delimiters delimiters;

    public InputParser() {
        delimiters = new Delimiters();
    }

    public PostFix parse(String s) {
        Matcher matcher = CUSTOM_PREFIX.matcher(s);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            validateCustomDelimiter(customDelimiter);

            delimiters.add(customDelimiter.charAt(0));

            s = validateNumberFormat(s, 5);
        } else {
            s = validateNumberFormat(s, 0);
        }

        String[] split = delimiters.split(s, 0);
        return new PostFix(infixToPostFix(s), needBigNumber(split));
    }

    private void validateCustomDelimiter(final String customDelimiter) {
        if (customDelimiter.length() != 1) { // 커스텀 구분자의 길이가 1이 아닌 경우
            throw new IllegalArgumentException();
        }
        if (customDelimiter.equals(".")) { // 커스텀 구분자가 온점인 경우
            throw new IllegalArgumentException();
        }
        char c = customDelimiter.charAt(0);
        if (Character.isDigit(c)) { // 커스텀 구분자가 숫자인 경우
            throw new IllegalArgumentException();
        }
    }

    private String validateNumberFormat(final String s, final int beginIndex) {
        String[] split = delimiters.split(s, beginIndex);
        if (isEmpty(split)) {
            return "0";
        }

        for (String str : split) {
            try {
                BigDecimal v = new BigDecimal(str);
                if (isNegative(v)) {
                    throw new IllegalArgumentException();
                }
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }

        return s.substring(beginIndex);
    }

    private boolean needBigNumber(final String[] postfix) {
        Operands operands = new Operands(postfix);
        return operands.needBigNumber();
    }

    private String infixToPostFix(String infix) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                result.append(c);
                if (i + 1 >= infix.length() ||
                    !Character.isDigit(infix.charAt(i + 1)) && infix.charAt(i + 1) != '.') {
                    result.append(' ');
                }
            } else {
                c = delimiters.delimiterToOperator(c);
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                    result.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString().trim();
    }

    private int priority(char c) {
        if (c == '+' || c == '-') {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isNegative(BigDecimal v) {
        return v.compareTo(BigDecimal.ZERO) <= 0;
    }

    private static boolean isEmpty(String[] split) {
        return split.length == 1 && split[0].isEmpty();
    }
}
