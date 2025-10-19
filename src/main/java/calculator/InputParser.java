package calculator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern CUSTOM_PREFIX = Pattern.compile("^//(.*)\\\\n");
    private static final Set<String> delimiters = new HashSet<>();

    public InputParser() {
        delimiters.add(",");
        delimiters.add("\\:");
    }

    public String[] parse(String s) {
        Matcher matcher = CUSTOM_PREFIX.matcher(s);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            validateCustomDelimiter(customDelimiter);
            delimiters.add(Pattern.quote(customDelimiter));

            return validateNumberFormat(s, 5);
        }
        return validateNumberFormat(s, 0);
    }

    private void validateCustomDelimiter(String customDelimiter) {
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

    private String[] validateNumberFormat(String s, int beginIndex) {
        String[] split = s.substring(beginIndex).split(String.join("|", delimiters));
        if (split.length == 1 && split[0].isEmpty()) {
            return new String[] {"0"};
        }

        for (String str : split) {
            try {
                BigDecimal v = new BigDecimal(str);
                if (v.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException();
                }
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }

        return split;
    }
}
