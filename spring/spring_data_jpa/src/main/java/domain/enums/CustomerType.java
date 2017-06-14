package domain.enums;

/**
 * 客户类型
 */
public enum CustomerType {
    /**
     * 个人
     */
    INDIVIDUAL,
    /**
     * 集团
     */
    GROUP,
    /**
     * 政企
     */
    GOV_ENT;

    public static CustomerType valueOf(int ordinal) {
        CustomerType[] values = CustomerType.values();
        if (ordinal >= values.length) {
            return null;
        } else {
            return values[ordinal];
        }
    }
}
