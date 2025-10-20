package calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    InputParser inputParser = new InputParser();

    @AfterEach
    void afterEach() {
        inputParser = new InputParser();
    }

    @Test
    void 기본_파싱_작업이_정상적으로_작동합니다() {
        PostFix postFix = inputParser.parse("1:2,3");
        Assertions.assertEquals("1 2 + 3 +", postFix.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\\n1:2;3", "// \\n1 2:3"})
    void 커스텀_구분자를_포함한_파싱_작업이_정상적으로_동작합니다(String v) {
        PostFix postFix = inputParser.parse(v);
        Assertions.assertEquals("1 2 + 3 +", postFix.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"// \n1 2;3,4"})
    void 등록되지_않은_구분자가_포함된_경우_예외가_발생합니다(String v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse(v));
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ";;"})
    void 커스텀_구분자의_길이가_2_이상인_경우_예외가_발생합니다(String v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse("//" + v + "\\n1:2;3,4"));
    }

    @Test
    void 커스텀_구분자의_길이가_0인_경우_예외가_발생합니다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse("//\\n1:2;3,4"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 커스텀_구분자가_숫자인_경우_예외가_발생합니다(int v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse("//" + v + "\\n1:2;3,4"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;n1;2", "//;\1;2", "/;\\n1;2", ";\\n1;2", "//;1;2", "/;n1;2", "/;\1;2", "/1;\\n1;2", "1/;\\n1;2"})
    void 커스텀_구분자를_잘못_표현한_경우_예외가_발생합니다(String v) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse(v));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\\n", ""})
    void 빈_문자열인_경우_0을_반환합니다(String v) {
        PostFix postFix = inputParser.parse(v);
        Assertions.assertEquals("0", postFix.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2.4:3.5", "//;\\n2.4:3.5"})
    void 소수점이_포함된_수를_정상적으로_파싱합니다(String v) {
        PostFix postFix = inputParser.parse(v);
        Assertions.assertEquals("2.4 3.5 +", postFix.getValue());
    }

    @Test
    void 온점은_커스텀_구분자로_사용할_수_없습니다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse("//.\\n2:4"));
    }

    @Test
    void 역슬래쉬를_커스텀_구분자로_인식합니다() {
        Assertions.assertEquals("4 3 +", inputParser.parse("//\\\\n4\\3").getValue());
    }

    @Test
    void 구분자를_2개_이상_연속으로_사용한_경우_예외가_발생합니다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parse("//;\\n1;2;;3"));
    }
}
