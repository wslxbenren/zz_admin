package com.xyz.modules.system.util;

import org.apache.commons.lang3.StringUtils;

public enum  ConstEnum {
    // 数据同步状态
    SYNC_ED("1", "已同步"),
    UN_SYNC("0", "未同步"),
    // 是否建立矫正小组
    IS("1", "是"),
    NOT("0", "否");



    private String value;
    private String desc;

    ConstEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String transSync(String status) {
        if (status == null || status.equals("")) {
            return "状态未知";
        }
        return status.equals("0") ? UN_SYNC.desc: SYNC_ED.desc;
    }

    public static String getBoolean(String code) {
        if (code == null || code.equals("")) {
            return "无数据";
        }
        return code.equals("1") ? IS.desc: NOT.desc;
    }

    public static String getBoolean(Integer code) {
        if(code == null) {
            return "无数据";
        }
        return code == 1? IS.desc: NOT.desc;
    }

    public static String genAddrPrefix(String addr) {
        if(addr.endsWith("0000")) {
            return addr.substring(0, 2);
        }
        if(addr.endsWith("00")) {
            return addr.substring(0, 4);
        }
        return addr;
    }
}
