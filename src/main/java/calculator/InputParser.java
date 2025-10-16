package calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern CUSTOM_PREFIX = Pattern.compile("^//(.)\\n");
    private static Set<String> delimiters = new HashSet<>();

    public InputParser() {
        delimiters.add("\\.");
        delimiters.add("\\:");
    }

    public String[] parse(String s) {
        Matcher matcher = CUSTOM_PREFIX.matcher(s);
        if (matcher.find()) { // 커스텀 구분자가 존재하는 경우
            String customDelimiter = matcher.group(1);
            delimiters.add(Pattern.quote(customDelimiter));
            return s.substring(5).split(String.join("|", delimiters));
        }

        return s.split(String.join("|", delimiters));
    }
}
