package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Machine {

    private Brain brain;
    private InputParser inputParser;

    public Machine() {
        this.brain = new Brain();
        this.inputParser = new InputParser();
    }

    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String line = Console.readLine();
        String[] parsed = inputParser.parse(line);
        String result = brain.plus(parsed);
        double d = Double.parseDouble(result);
        if (d - (int) d == 0) {
            result = String.valueOf((int) d);
        }
        System.out.println("결과 : " + result);
    }
}
