package domain.enums;

/**
 * 性别
 */
public enum Sex {
    /**
     * 女
     */
    WOMAN,
    /**
     * 男
     */
    MAN;

    public static Sex valueOf(int ordinal) {
        Sex[] values = Sex.values();
        if (ordinal >= values.length) {
            return null;
        } else {
            return values[ordinal];
        }
    }
}
