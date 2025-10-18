package calculator;

public class Brain {
    public String plus(String[] input) {
        double sum = 0;
        for (String s : input) {
            sum += Double.parseDouble(s);
        }
        return String.valueOf(sum);
    }
}
