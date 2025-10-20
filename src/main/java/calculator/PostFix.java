package calculator;

public class PostFix {

    private String value;
    private boolean needBigNumber;

    public PostFix(String value, boolean needBigNumber) {
        this.value = value;
        this.needBigNumber = needBigNumber;
    }

    public boolean getNeedBigNumber() {
        return this.needBigNumber;
    }

    public String getValue() {
        return this.value;
    }
}
