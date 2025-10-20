package calculator.delimiter;

import calculator.operator.Operators;
import calculator.operator.Plus;
import java.util.ArrayList;
import java.util.List;

public class Delimiters {

    private List<Delimiter> delimiters;

    public Delimiters() {
        this.delimiters = new ArrayList<>();
        this.delimiters.add(new Delimiter(',', Operators.getOperator(Plus.class)));
        this.delimiters.add(new Delimiter(':', Operators.getOperator(Plus.class)));
    }

    public char delimiterToOperator(char c) {
        Delimiter delimiter = delimiters.stream().filter(d -> d.equals(c))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 구분자입니다"));
        return delimiter.getOperatorCharacter();
    }

    public void add(char c) {
        this.delimiters.add(new Delimiter(c, Operators.getOperator(Plus.class)));
    }

    public String[] split(String s, int beginIndex) {
        String[] cs = delimiters.stream()
                .map(d -> "\\" + d.getValue())
                .toArray(String[]::new);
        return s.substring(beginIndex).split(String.join("|", cs));
    }
}
