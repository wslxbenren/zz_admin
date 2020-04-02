package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;


/**
* @author dadovicn
* @date 2020-02-02
*/
@Data
public class T4fromwuhanScreeningworkDTO implements Serializable {

    // 主键
    private String id;

    // 单位编码
    private String deptCode;

    // 单位名称
    private String deptName;

    // 类型（1市局下发，2自排发现）
    private BigDecimal sourceType;

    // 姓名
    private String peopleName;

    // 身份证号
    private String idno;

    // 人员联系方式
    private String peopleMobile;

    // 在郑实际居住地址
    private String addr;

    // 排查民警警号
    private String userid;

    // 排查民警姓名
    private String username;

    // 是否异常（1是0否）
    private BigDecimal ifAbmormal;

    // 核查日期
    private Timestamp checkTime;

    // 填报人
    private String fillPerson;

    // 联系方式
    private String mobile;

    // 填报时间
    private Timestamp fillTime;

    // 更新时间
    private Timestamp updateTime;
}