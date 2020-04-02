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
@Table(name="t_4fromwuhan_screeningwork")
public class T4fromwuhanScreeningwork implements Serializable {

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

    // 类型（1市局下发，2自排发现）
    @Column(name = "t_type")
    private BigDecimal sourceType;

    // 姓名
    @Column(name = "people_name")
    private String peopleName;

    // 身份证号
    @Column(name = "idno")
    private String idno;

    // 人员联系方式
    @Column(name = "people_mobile")
    private String peopleMobile;

    // 在郑实际居住地址
    @Column(name = "addr")
    private String addr;

    // 排查民警警号
    @Column(name = "userid")
    private String userid;

    // 排查民警姓名
    @Column(name = "username")
    private String username;

    // 是否异常（1是0否）
    @Column(name = "if_abmormal")
    private BigDecimal ifAbmormal;

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

    public void copy(T4fromwuhanScreeningwork source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}