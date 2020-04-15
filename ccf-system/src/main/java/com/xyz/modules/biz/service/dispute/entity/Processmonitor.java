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
@Table(name="biz_dispute_processmonitor")
public class Processmonitor implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "process_id")
    private String processId;

    // 事件编号，跟事件基本信息关联
    @Column(name = "event_code")
    private String eventCode;

    // 处置环节
    @Column(name = "disposal_link")
    private String disposalLink;

    // 处理单位
    @Column(name = "disposal_unit")
    private String disposalUnit;

    // 处理时间
    @Column(name = "disposal_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp disposalTime;

    // 处理人
    @Column(name = "disposal_name")
    private String disposalName;

    // 处理意见
    @Column(name = "disposal_opinion")
    private String disposalOpinion;

    // 核查人
    @Column(name = "check_name")
    private String checkName;

    // 核查结果
    @Column(name = "check_result")
    private String checkResult;

    // 核查意见
    @Column(name = "checkl_opinion")
    private String checklOpinion;

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

    public void copy(Processmonitor source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}