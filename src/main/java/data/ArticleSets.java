package data;

public enum ArticleSets {
    TRAIN(0),
    TEST(1);

    private final int intValue;

    ArticleSets(int byteValue) {
        this.intValue = byteValue;
    }

    public int getIntValue() {
        return this.intValue;
    }
}
