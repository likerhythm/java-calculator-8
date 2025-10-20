package calculator.operand;

import java.util.ArrayList;
import java.util.List;

public class Operands {

    private static final int MAX_SIZE = 100;
    private final List<Operand> operands;
    private final int maxIntLength;
    private final int maxDecimalLength;
    private boolean hasBigNumber;

    public Operands(final String[] strings) {
        operands = new ArrayList<>();
        for (String str : strings) {
            Operand operand = new Operand(str);
            operands.add(operand);
            if (operand.isBigNumber()) hasBigNumber = true;
        }
        maxIntLength = operands.stream().mapToInt(Operand::getIntLength).max().getAsInt();
        maxDecimalLength = operands.stream().mapToInt(Operand::getDecimalLength).max().getAsInt();
        hasBigNumber = false;
    }

    public boolean needBigNumber() {
        return maxIntLength + maxDecimalLength > Operand.MAX_LENGTH || hasBigNumber || operands.size() > MAX_SIZE;
    }
}
