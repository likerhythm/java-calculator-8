package calculator;

import camp.nextstep.edu.missionutils.Console;

public class IOUnit {

    public String read() {
        return Console.readLine();
    }

    public void write(String s) {
        System.out.println(s);
    }
}
