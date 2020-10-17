package com.example.jhyangnewthings.common.constant;

/**
 * @Author jhYang
 * @Date 2020/4/16 0016 9:22
 * @Discription todo  枚举示例
 */
public enum EnumTest {

    SPRING("春天","123月"),
    SUMMER("夏天","456月"),
    autumn("秋天","789月"),
    winter("冬天","10,11，12月");

    private String name;
    private String month;

    EnumTest(String name, String month) {
        this.name = name;
        this.month = month;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
