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
public class T3commandcenterIndustryDTO implements Serializable {

    // 主键
    private String id;

    // 日期
    private Timestamp fillDate;

    // 接警电话
    private String alarmCall;

    // 接警员工号
    private String userid;

    // 接警员姓名
    private String username;

    // 警情来源（1群众举报，2卫生防疫部门通报，3当事人自己报警三选一）
    private BigDecimal source;

    // 事发地址
    private String caseAddr;

    // 报警内容
    private String content;

    // 反馈情况
    private String feedback;

    // 涉及人数
    private BigDecimal personNo;

    // 报告情况(疑似或者确诊的，要标注清楚在家或者在医院隔离）
    private String fillContent;

    // 隔离场所说明（1在家，2医院二选一，无感染0）
    private BigDecimal ifIsolation;

    // 隔离详细地址或医院名称
    private String isolationAddr;

    // 所属分局代码
    private String deptCode;

    // 所属分局名称
    private String deptName;

    // 备注
    private String remark;

    // 更新时间
    private Timestamp updateDate;
}