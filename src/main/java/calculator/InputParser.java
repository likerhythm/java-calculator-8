package calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern CUSTOM_PREFIX = Pattern.compile("^//(.*)\\n");
    private static Set<String> delimiters = new HashSet<>();

    public InputParser() {
        delimiters.add("\\.");
        delimiters.add("\\:");
    }

    public String[] parse(String s) {
        Matcher matcher = CUSTOM_PREFIX.matcher(s);
        if (matcher.find()) { // 커스텀 구분자가 존재하는 경우
            String customDelimiter = matcher.group(1);
            validateCustomDelimiter(customDelimiter);
            delimiters.add(Pattern.quote(customDelimiter));

            return validateNumberFormat(s, 4);
        }
        return validateNumberFormat(s, 0);
    }

    private void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.length() != 1) { // 커스텀 구분자의 길이가 1이 아닌 경우
            throw new IllegalArgumentException();
        }
        char c = customDelimiter.charAt(0);
        if (Character.isDigit(c)) { // 커스텀 구분자가 숫자인 경우
            throw new IllegalArgumentException();
        }
    }

                throw new IllegalArgumentException();
            }
            delimiters.add(Pattern.quote(customDelimiter));
            return s.substring(4).split(String.join("|", delimiters));
        }

        return s.split(String.join("|", delimiters));
    }
}
