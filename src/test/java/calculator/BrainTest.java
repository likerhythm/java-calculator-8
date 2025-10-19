package calculator;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BrainTest {

    Brain brain = new Brain();

    @Test
    void 매우_긴_소수의_계산을_오차_없이_정상적으로_수행합니다() {
        String a = "123.1234567891234567";
        String b = "123.1234567891234567";
        Assertions.assertEquals("246.2469135782469134", brain.plus(new String[] {a, b}));
    }

    @Test
    void 매우_큰_수의_계산을_정상적으로_수행합니다() {
        String a = "100000000000000000000000000"; // 10조 * 10조
        String b = "100000000000000000000000000";
        Assertions.assertEquals("200000000000000000000000000", brain.plus(new String[] {a, b}));
    }

    @Test
    void 작은_수를_계산하는_시간이_큰_수를_계산하는_시간보다_빠릅니다() {
        String[] smalls = new String[1000];
        Arrays.fill(smalls, "1");
        String[] bigs = new String[1000];
        Arrays.fill(bigs, "100000000000000000000000000");

        long totalSmallTime = 0, totalBigTime = 0;
        for (int i = 0; i < 1000; i++) {
            long startTime = System.currentTimeMillis();
            brain.plus(smalls);
            long endTime = System.currentTimeMillis();
            totalSmallTime += endTime - startTime;

            startTime = System.currentTimeMillis();
            brain.plus(bigs);
            endTime = System.currentTimeMillis();
            totalBigTime += endTime - startTime;
        }

        double avgSmallTime = (double) totalSmallTime / 1000;
        double avgBigTime = (double) totalBigTime / 1000;

        Assertions.assertTrue(avgSmallTime < avgBigTime);
    }
}
