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
@Table(name="t_3commandcenter_industry")
public class T3commandcenterIndustry implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 日期
    @Column(name = "fill_date", updatable = false)
    @CreationTimestamp
    private Timestamp fillDate;

    // 接警电话
    @Column(name = "alarm_call")
    private String alarmCall;

    // 接警员工号
    @Column(name = "userid")
    private String userid;

    // 接警员姓名
    @Column(name = "username")
    private String username;

    // 警情来源（1群众举报，2卫生防疫部门通报，3当事人自己报警三选一）
    @Column(name = "source")
    private BigDecimal source;

    // 事发地址
    @Column(name = "case_addr")
    private String caseAddr;

    // 报警内容
    @Column(name = "content")
    private String content;

    // 反馈情况
    @Column(name = "feedback")
    private String feedback;

    // 涉及人数
    @Column(name = "person_no")
    private BigDecimal personNo;

    // 报告情况(疑似或者确诊的，要标注清楚在家或者在医院隔离）
    @Column(name = "fill_content")
    private String fillContent;

    // 隔离场所说明（1在家，2医院二选一，无感染0）
    @Column(name = "if_isolation")
    private BigDecimal ifIsolation;

    // 隔离详细地址或医院名称
    @Column(name = "isolation_addr")
    private String isolationAddr;

    // 所属分局代码
    @Column(name = "dept_code")
    private String deptCode;

    // 所属分局名称
    @Column(name = "dept_name")
    private String deptName;

    // 备注
    @Column(name = "remark")
    private String remark;

    // 更新时间
    @Column(name = "update_date")
    @UpdateTimestamp
    private Timestamp updateDate;

    public void copy(T3commandcenterIndustry source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}