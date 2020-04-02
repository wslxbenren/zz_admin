package com.xyz.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
* @date 2020-02-07
*/
@Entity
@Data
@Table(name="t_suspected_personnel_screening")
public class SuspectedPersonnelScreening implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 人员编号
    @Column(name = "p_id")
    private String pId;

    // 排查人
    @Column(name = "entry_name")
    private String entryName;

    // 排查登记时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    @Column(name = "screen_time")
    private Timestamp screenTime;

    // 目前健康状况1正常2体温偏高有咳嗽3其他
    @Column(name = "if_health")
    private BigDecimal ifHealth;

    // 目前所在城市地区
    @Column(name = "p_city")
    private String pCity;

    // 隔离开始日期
    @Column(name = "stay_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp stayTime;

    // 是否接触湖北人员
    @Column(name = "if_hubei")
    private BigDecimal ifHubei;

    // 是否接触病例
    @Column(name = "if_contact")
    private BigDecimal ifContact;

    // 接触时间
    @Column(name = "contact_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp contactTime;

    // 接触说明
    @Column(name = "contact_info")
    private String contactInfo;

    // 外出说明
    @Column(name = "out_info")
    private String outInfo;

    // 外出时间
    @Column(name = "out_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp outTime;

    // 录入时间
    @Column(name = "entry_time")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp entryTime;

    // 录入人单位
    @Column(name = "entry_unit")
    private String entryUnit;

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    @Column(name = "apply_status")
    private BigDecimal applyStatus;

    public void copy(SuspectedPersonnelScreening source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}