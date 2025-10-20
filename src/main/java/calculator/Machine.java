package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Machine {

    private final Brain brain;
    private final InputParser inputParser;

    public Machine() {
        this.brain = new Brain();
        this.inputParser = new InputParser();
    }

    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String line = Console.readLine();
        PostFix postfix = inputParser.parse(line);
        String result = brain.plus(postfix);
        String[] split = result.split("\\.");
        if (split[1].equals("0")) {
            result = split[0];
        }
        System.out.println("결과 : " + result);
        Console.close();
    }
}
