package com.xyz.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Entity
@Data
@Table(name="t_2socialpatrol_workingday")
public class T2socialpatrolWorkingday implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 日期
    @Column(name = "check_time")
    private Timestamp checkTime;

    // 单位编码
    @Column(name = "dept_code")
    private String deptCode;

    // 单位名称
    @Column(name = "dept_name")
    private String deptName;

    // 巡逻防控投入民警数
    @Column(name = "patrol_police")
    private BigDecimal patrolPolice;

    // 巡逻防控投入辅警数
    @Column(name = "patrol_auxiliary")
    private BigDecimal patrolAuxiliary;

    // 群防群治力量总数
    @Column(name = "patrol_other")
    private BigDecimal patrolOther;

    // 启动公安检查站数
    @Column(name = "checkpoint_no")
    private BigDecimal checkpointNo;

    // 临时执勤点数
    @Column(name = "checkpoint_tempno")
    private BigDecimal checkpointTempno;

    // 联合防疫检查站数
    @Column(name = "checkpoint_unionno")
    private BigDecimal checkpointUnionno;

    // 检查站投入民警数
    @Column(name = "check_police")
    private BigDecimal checkPolice;

    // 检查站投入辅警数
    @Column(name = "check_auxiliary")
    private BigDecimal checkAuxiliary;

    // 检查车辆数
    @Column(name = "check_car")
    private BigDecimal checkCar;

    // 检查人员数
    @Column(name = "check_person")
    private BigDecimal checkPerson;

    // 发现协助转达疑似感染数
    @Column(name = "assist_no")
    private BigDecimal assistNo;

    // 发现核查涉稳线索数
    @Column(name = "clue_no")
    private BigDecimal clueNo;

    // 处置涉疫情稳定事件数
    @Column(name = "case_no")
    private BigDecimal caseNo;

    // 部署警力应急备勤数
    @Column(name = "emergency_no")
    private BigDecimal emergencyNo;

    // 填报人
    @Column(name = "fill_person")
    private String fillPerson;

    // 联系方式
    @Column(name = "mobile")
    private String mobile;

    // 填报时间
    @Column(name = "fill_time", updatable = false)
    @CreationTimestamp
    private Timestamp fillTime;

    // 签发人
    @Column(name = "issue_name")
    private String issueName;

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    public void copy(T2socialpatrolWorkingday source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}