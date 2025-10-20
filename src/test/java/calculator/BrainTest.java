package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BrainTest {

    Brain brain = new Brain();

    @Test
    void 매우_긴_소수의_계산을_오차_없이_정상적으로_수행합니다() {
        String a = "123.1234567891234567";
        String b = "123.1234567891234567";
        Assertions.assertEquals("246.2469135782469134", brain.run(new PostFix(a + " " + b + " +", true)));
    }

    @Test
    void 매우_큰_수의_계산을_정상적으로_수행합니다() {
        String a = "100000000000000000000000000"; // 10조 * 10조
        String b = "100000000000000000000000000";
        Assertions.assertEquals("200000000000000000000000000", brain.run(new PostFix(a + " " + b + " +", true)));
    }
}
