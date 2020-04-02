package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;


/**
* @author dadovicn
* @date 2020-02-07
*/
@Data
public class SuspectedPersonnelScreeningDTO implements Serializable {

    // 主键
    private String id;

    // 人员编号
    private String pId;

    // 排查人
    private String entryName;

    // 排查登记时间
    private Timestamp screenTime;

    // 目前健康状况1正常2体温偏高有咳嗽3其他
    private BigDecimal ifHealth;

    // 目前所在城市地区
    private String pCity;

    // 隔离开始日期
    private Timestamp stayTime;

    // 是否接触湖北人员
    private BigDecimal ifHubei;

    // 是否接触病例
    private BigDecimal ifContact;

    // 接触时间
    private Timestamp contactTime;

    // 接触说明
    private String contactInfo;

    // 外出说明
    private String outInfo;

    // 外出时间
    private Timestamp outTime;

    // 录入时间
    private Timestamp entryTime;

    // 录入人单位
    private String entryUnit;

    // 更新时间
    private Timestamp updateTime;

    // 登记状态
    private BigDecimal applyStatus;
}