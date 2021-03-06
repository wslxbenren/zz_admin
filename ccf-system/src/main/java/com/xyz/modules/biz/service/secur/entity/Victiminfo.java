package com.xyz.modules.biz.service.secur.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.domain.Dept;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：社会治安管理/命案受害人信息
 */
@Entity
@Data
@DynamicUpdate
@Table(name="biz_secur_victiminfo")
public class Victiminfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "vic_id")
    private String vicId;

    // 受害人编号
    @Column(name = "victim_code")
    private String victimCode;

//    // 案件编号
    @Column(name = "case_code")
    private String caseCode;

    // 证件代码
    @Column(name = "card_type")
    private String cardType;

    // 证件号码
    @Column(name = "card_code")
    private String cardCode;

    // 姓名
    @Column(name = "person_name")
    private String personName;

    // 性别
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期
    @Column(name = "date_birth")
    private Timestamp dateBirth;

    // 国籍（地区）
    @Column(name = "country")
    private String country;

    // 民族
    @Column(name = "nation")
    private String nation;

    // 籍贯
    @Column(name = "native_info")
    private String nativeInfo;

    // 籍贯详址
    @Column(name = "native_addr")
    private String nativeAddr;

    // 婚姻状况
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌
    @Column(name = "party_flag")
    private String partyFlag;

    // 学历
    @Column(name = "education_bg")
    private String educationBg;

    // 宗教信仰
    @Column(name = "faith_type")
    private String faithType;

    // 职业类别
    @Column(name = "vocation_code")
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务处所省市县编码
    @Column(name = "service_addrcode")
    private String serviceAddrcode;

    // 服务处所
    @Column(name = "service_addr")
    private String serviceAddr;

    // 户籍地
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    private String registeredAddr;

    // 现住地
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 生效时间
    @Column(name = "eff_date",insertable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",insertable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
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
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable=false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Victiminfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "case_code")
//    @JsonIgnore
//    private BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo;

}