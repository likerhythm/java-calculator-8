package calculator;

import java.math.BigDecimal;

public class Brain {

    private static final int MAX_LENGTH = 12;

    public String plus(String[] input) {
        boolean isBig = false;
        int maxIntLength = 0, maxDecimalLength = 0;
        for (String str : input) {
            if (str.contains(".")) { // 소수인 경우
                if (str.length() >= MAX_LENGTH + 1) { // 소수점 포함해서 총 13길이인 경우(12자리 숫자인 경우)
                    isBig = true;
                    break;
                }
                String[] split = str.split("/.");
                String intPart = split[0];
                String decimalPart = split[1];
                maxIntLength = Math.max(maxIntLength, intPart.length());
                maxDecimalLength = Math.max(maxDecimalLength, decimalPart.length());
            } else if (str.length() > MAX_LENGTH) {
                isBig = true;
                break;
            }
        }

        if (maxIntLength + maxDecimalLength > MAX_LENGTH) { // 개산 결과의 정수부분의 길이와 소수부분의 길이의 합이 12를 넘어가는 경우
            isBig = true;
        } else if (input.length > 100) { // 피연산자 개수가 너무 많은 경우
            isBig = true;
        }

        if (isBig) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String s : input) {
                BigDecimal v = new BigDecimal(s);
                sum = sum.add(v);
            }
            return String.valueOf(sum);
        } else {
            double sum = 0;
            for (String s : input) {
                sum += Double.parseDouble(s);
            }
            return String.valueOf(sum);
        }
    }
}
