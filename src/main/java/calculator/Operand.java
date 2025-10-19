package calculator;

public class Operand {

    public static final int MAX_LENGTH = 12;

    private final String intPart;
    private final String decimalPart;

    public Operand(String str) {
        String[] split = str.split("/.");
        intPart = split[0];
        if (split.length == 2) {
            decimalPart = split[1];
        } else {
            decimalPart = "";
        }
    }

    public boolean isBigNumber() {
        return intPart.length() + decimalPart.length() > MAX_LENGTH;
    }

    public int getIntLength() {
        return intPart.length();
    }

    public int getDecimalLength() {
        return decimalPart.length();
    }
}
