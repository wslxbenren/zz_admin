package com.xyz.modules.biz.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Entity
@Data
@Table(name="biz_special_drug_person")
public class DrugPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "drug_id")
    private String drugId;

    // 姓名
    @Column(name = "person_name",nullable = false)
    private String personName;

    // 曾用名
    @Column(name = "used_name",nullable = false)
    private String usedName;

    // 性别
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期
    @Column(name = "date_birth")
    private Timestamp dateBirth;

    // 民族
    @Column(name = "nation",nullable = false)
    private String nation;

    // 身份证号
    @Column(name = "identity_num",unique = true,nullable = false)
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

    // 现住地，跟现住地编码residence_code数据重复存储
    @Column(name = "residence")
    private String residence;

    // 现住地编码
    @Column(name = "residence_code")
    private String residenceCode;

    // 现住地详细地址
    @Column(name = "residence_address")
    private String residenceAddress;

    // 初次发现日期
    @Column(name = "find_date",nullable = false)
    private Timestamp findDate;

    // 管控情况
    @Column(name = "manage_type",nullable = false)
    private String manageType;

    // 管控人姓名
    @Column(name = "manager_name",nullable = false)
    private String managerName;

    // 管控人联系方式
    @Column(name = "manager_address",nullable = false)
    private String managerAddress;

    // 帮扶情况
    @Column(name = "helpe_comment")
    private String helpeComment;

    // 帮扶人姓名
    @Column(name = "helper_name",nullable = false)
    private String helperName;

    // 帮扶人联系方式
    @Column(name = "helper_address",nullable = false)
    private String helperAddress;

    // 是否有犯罪史
    @Column(name = "is_pedigree")
    private String isPedigree;

    // 犯罪情况
    @Column(name = "crime_comment")
    private String crimeComment;

    // 吸毒原因
    @Column(name = "drug_reason")
    private String drugReason;

    // 吸毒后果
    @Column(name = "drug_result")
    private String drugResult;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
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
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 职业类别
    @Column(name = "vocation_code")
    private String vocationCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    public void copy(DrugPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}