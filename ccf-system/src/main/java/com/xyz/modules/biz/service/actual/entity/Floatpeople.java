package com.xyz.modules.biz.service.actual.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Entity
@Data
@Table(name="biz_actual_floatpeople")
@DynamicUpdate
public class Floatpeople implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "float_id")
    private String floatId;

    // 公民身份号码:编码应符合GB/T11643
    @Column(name = "identity_num",unique = true)
    private String identityNum;

    // 姓名
    @Column(name = "person_name")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    @Column(name = "nation")
    private String nation;

    // 籍贯:编码应符合GB/T2260
    @Column(name = "native_info")
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    @Column(name = "party_flag")
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    @Column(name = "education_bg")
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    @Column(name = "faith_type")
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    private String contact;

    // 户籍地:编码应符合GB/T2260
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 流入原因
    @Column(name = "into_cause")
    private String intoCause;

    // 办证类型
    @Column(name = "card_type")
    private String cardType;

    // 证件号码
    @Column(name = "card_no")
    private String cardNo;

    // 登记日期:格式为“YYYYMMDD”
    @Column(name = "regis_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp regisDate;

    // 证件到期日期:格式为“YYYYMMDD”
    @Column(name = "expiry_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp expiryDate;

    // 住所类型
    @Column(name = "resid_type")
    private String residType;

    // 是否重点关注人员
    @Column(name = "if_import")
    private Integer ifImport;

    // 生效时间
    @Column(name = "eff_date", insertable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",updatable = false)
    @UpdateTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    public void copy(Floatpeople source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }


}