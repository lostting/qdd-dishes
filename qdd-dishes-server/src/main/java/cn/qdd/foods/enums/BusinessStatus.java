package cn.qdd.foods.enums;

/**
 * 操作状态
 *
 * @author 东方曜
 */
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 失败
     */
    FAIL(-1);
    private Integer value;

    BusinessStatus(Integer s) {
        this.value = s;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
