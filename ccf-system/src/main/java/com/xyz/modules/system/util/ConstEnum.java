package com.xyz.modules.system.util;

public enum  ConstEnum {
    // 数据同步状态
    SYNC_ED("1", "已同步"),
    UN_SYNC("0", "未同步"),
    // 是否建立矫正小组
    IS("1", "是"),
    NOT("1", "否");

    private String value;
    private String desc;

    ConstEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String transSync(String status) {
        if (status == null) {
            return "状态未知";
        }
        return status == "0" ? UN_SYNC.desc: SYNC_ED.desc;
    }

    public static String getBoolean(String code) {
        if (code == null) {
            return "无数据";
        }
        return code == "1"? IS.desc: NOT.desc;
    }

    public static String getBoolean(Integer code) {
        if(code == null) {
            return "无数据";
        }
        return code == 1? IS.desc: NOT.desc;
    }
}
