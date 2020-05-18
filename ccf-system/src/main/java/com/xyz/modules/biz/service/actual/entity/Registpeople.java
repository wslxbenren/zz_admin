package com.xyz.modules.biz.service.actual.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import com.xyz.modules.system.util.annotation.FieldAlias;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author lx
 * @date 2020-04-08
 */
@Entity
@Data
@Table(name = "biz_actual_registpeople")
@DynamicUpdate
public class Registpeople implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "regis_id")
    @FieldAlias("Id")
    private String regisId;

    // 身份证号:编码应符合GB/T11643
    @Column(name = "identity_num", unique = true)
    @FieldAlias("身份证号")
    private String identityNum;

    // 姓名
    @Column(name = "person_name")
    @FieldAlias("姓名")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    @FieldAlias("曾用名")
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    @FieldAlias("性别")
    @Dict(DictEnum.XING_BIE)
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("出生日期")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    @Column(name = "nation")
    @FieldAlias("民族")
    @Dict(DictEnum.MIN_ZU)
    private String nation;

    // 籍贯:编码应符合GB/T2260
    @Column(name = "native_info")
    @FieldAlias("籍贯")
    @Dict(DictEnum.ADDRESS)
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    @Column(name = "marriage_flag")
    @FieldAlias("婚姻状况")
    @Dict(DictEnum.HYZK)
    private String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    @Column(name = "party_flag")
    @FieldAlias("政治面貌")
    @Dict(DictEnum.ZZMM)
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    @Column(name = "education_bg")
    @FieldAlias("学历")
    @Dict(DictEnum.XUE_LI)
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    @Column(name = "faith_type")
    @FieldAlias("宗教信仰")
    @Dict(DictEnum.ZJXY)
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    @FieldAlias("职业类别")
    @Dict(DictEnum.ZYLB)
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    @FieldAlias("职业")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    @FieldAlias("服务处所")
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    @FieldAlias("联系方式")
    private String contact;

    // 户籍地:编码应符合GB/T2260
    @Column(name = "registered_place")
    @FieldAlias("户籍地")
    @Dict(DictEnum.ADDRESS)
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    @FieldAlias("户籍门（楼）详址")
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    @Column(name = "residence")
    @FieldAlias("现住地")
    @Dict(DictEnum.ADDRESS)
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    @FieldAlias("现住门（楼）详址")
    private String residenceAddr;

    // 人户一致标识:01：一致,02：不一致
    @Column(name = "household_id")
    @FieldAlias("人户一致标识")
    @Dict(DictEnum.RHYZBS)
    private String householdId;

    // 户号
    @Column(name = "door_no")
    @FieldAlias("户号")
    private String doorNo;

    // 户主公民身份号码:编码应符合GB/T11643
    @Column(name = "househead_idno")
    @FieldAlias("户主公民身份号码")
    private String househeadIdno;

    // 户主姓名
    @Column(name = "househead_name")
    @FieldAlias("户主姓名")
    private String househeadName;

    // 与户主关系:编码应符合GB/T4761
    @Column(name = "househead_rela")
    @Dict(DictEnum.YHZGX)
    @FieldAlias("与户主关系")
    private String househeadRela;

    // 户主联系方式:手机号码或固定电话号码
    @Column(name = "househead_mobile")
    @FieldAlias("户主联系方式")
    private String househeadMobile;

    // 生效时间
    @Column(name = "eff_date", insertable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("生效时间")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("失效时间")
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    @FieldAlias("同步状态")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    @FieldAlias("数据状态")
    @Dict(DictEnum.SJZT)
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    @FieldAlias("操作人名称")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("操作时间")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator", updatable = false)
    @FieldAlias("创建人")
    private String creator;

    // 创建时间
    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("创建时间")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    @FieldAlias("所属单位")
    private String unitCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    @FieldAlias("籍贯详址")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    @FieldAlias("服务处所的省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String servicePlaceCode;

    public void copy(Registpeople source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}