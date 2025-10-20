package calculator.delimiter;

import calculator.operator.Operator;

public class Delimiter {

    private final char value;
    private final Operator operator;

    public Delimiter(char value, Operator operator) {
        this.value = value;
        this.operator = operator;
    }

    public char getOperatorCharacter() {
        return operator.getCharacter();
    }

    public boolean equals(char target) {
        return value ==  target;
    }

    public char getValue() {
        return value;
    }
}
