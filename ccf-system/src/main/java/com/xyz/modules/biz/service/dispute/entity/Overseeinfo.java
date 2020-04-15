package com.xyz.modules.biz.service.dispute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Entity
@Data
@Table(name="biz_dispute_overseeinfo")
public class Overseeinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "oversee_id")
    private String overseeId;

    // 事件编号，跟事件基本信息关联
    @Column(name = "event_code")
    private String eventCode;

    // 督办单位
    @Column(name = "oversee_unit")
    private String overseeUnit;

    // 督办人
    @Column(name = "oversee_name")
    private String overseeName;

    // 督办时间
    @Column(name = "oversee_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp overseeTime;

    // 督办意见
    @Column(name = "oversee_opinion")
    private String overseeOpinion;

    // 督办材料
    @Column(name = "oversee_material")
    private String overseeMaterial;

    // 备注
    @Column(name = "remark")
    private String remark;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Overseeinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}