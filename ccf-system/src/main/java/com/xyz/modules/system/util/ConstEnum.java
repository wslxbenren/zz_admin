package com.xyz.modules.system.util;

public enum  ConstEnum {
    // 数据同步状态
    SYNC_ED("1", "已同步"),
    UN_SYNC("0", "未同步"),
    // 是否是惯犯
    IS_RECIDIVIST("1", "惯犯"),
    NOT_RECIDIVIST("0", "非惯犯");


    private String value;
    private String desc;

    ConstEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String transSync(String status) {
        return status == "0" ? UN_SYNC.desc: SYNC_ED.desc;
    }

    public static String transGuanFan(String recidivist) {
        return recidivist == "1" ? IS_RECIDIVIST.desc: NOT_RECIDIVIST.desc;
    }
}
