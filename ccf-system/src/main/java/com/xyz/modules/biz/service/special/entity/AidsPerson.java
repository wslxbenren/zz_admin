package com.xyz.modules.biz.service.special.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author 刘鑫
 * @date 2020-04-10
 * 艾滋病危险人群基础信息表
 */
@Entity
@Data
@Table(name="biz_special_aids_person")
@DynamicUpdate
public class AidsPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "aids_id")
    private String aidsId;

    // 姓名
    @Column(name = "person_name")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    private String usedName;

    // 性别
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp dateBirth;

    // 民族
    @Column(name = "nation")
    private String nation;

    // 身份证号
    @Column(name = "identity_num",unique = true)
    private String identityNum;

    // 籍贯
    @Column(name = "native_info")
    private String nativeInfo;

    // 婚姻状况
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌
    @Column(name = "party_flag")
    private String partyFlag;

    // 文化程度
    @Column(name = "edu_level")
    private String eduLevel;

    // 宗教信仰
    @Column(name = "faith_type")
    private String faithType;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务场所
    @Column(name = "service_place")
    private String servicePlace;

    // 联系方式
    @Column(name = "contact")
    private String contact;

    // 户籍地
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍详细地址
    @Column(name = "registered_address")
    private String registeredAddress;

    // 感染途径
    @Column(name = "routes_infection")
    private String routesInfection;

    // 是否有犯罪史
    @Column(name = "is_pedigree")
    private String isPedigree;

    // 犯罪情况说明
    @Column(name = "pedigree_comments")
    private String pedigreeComments;

    // 案件类别
    @Column(name = "case_type")
    private String caseType;

    // 关注类型
    @Column(name = "take_type")
    private String takeType;

    // 帮扶情况
    @Column(name = "help_comments")
    private String helpComments;

    // 帮扶人姓名
    @Column(name = "helper_name")
    private String helperName;

    // 帮扶人联系方式
    @Column(name = "helper_address")
    private String helperAddress;

    // 收治情况
    @Column(name = "detain_type")
    private String detainType;

    // 收治机构名称
    @Column(name = "detain_union")
    private String detainUnion;

    // 生效时间
    @Column(name = "eff_date", insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false)
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
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 职业类别
    @Column(name = "vocation_code")
    private String vocationCode;

    // 现住地
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 籍贯详址
    @Column(name = "native_info_addr")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    public void copy(AidsPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}