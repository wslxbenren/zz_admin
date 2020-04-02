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
@Table(name="t_5keyperson_screeningwork")
public class T5keypersonScreeningwork implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 单位编码
    @Column(name = "dept_code")
    private String deptCode;

    // 单位名称
    @Column(name = "dept_name")
    private String deptName;

    // 下发人数
    @Column(name = "issued_no")
    private BigDecimal issuedNo;

    // 自排人数
    @Column(name = "self_no")
    private BigDecimal selfNo;

    // 当日总数
    @Column(name = "sum_no")
    private BigDecimal sumNo;

    // 核查日期
    @Column(name = "check_time")
    private Timestamp checkTime;

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

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    public void copy(T5keypersonScreeningwork source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}