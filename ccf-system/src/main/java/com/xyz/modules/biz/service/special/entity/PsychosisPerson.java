package com.xyz.modules.biz.service.special.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 特殊人群有精神病肇事人员基础信息表
 */
@Entity
@Data
@Table(name="biz_special_psychosis_person")
@DynamicUpdate
public class PsychosisPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "psychosis_id")
    private String psychosisId;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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

    // 现住地，跟现住地编码residence_code数据重复存储
    @Column(name = "residence")
    private String residence;

    // 现住地编码
    @Column(name = "residence_code")
    private String residenceCode;

    // 现住地详细地址
    @Column(name = "residence_address")
    private String residenceAddress;

    // 家庭经济情况
    @Column(name = "source_income")
    private String sourceIncome;

    // 是否纳入低保
    @Column(name = "is_basicliving")
    private String isBasicliving;

    // 监护人身份证号
    @Column(name = "guarder_identity")
    private String guarderIdentity;

    // 监护人姓名
    @Column(name = "guarder_name")
    private String guarderName;

    // 监护人联系方式
    @Column(name = "guarder_address")
    private String guarderAddress;

    // 初次发病日期
    @Column(name = "attack_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp attackDate;

    // 诊断类型
    @Column(name = "diagnose_type")
    private String diagnoseType;

    // 有无肇祸史
    @Column(name = "is_trouble")
    private String isTrouble;

    // 肇祸次数
    @Column(name = "trouble_times")
    private Integer troubleTimes;

    // 上次肇祸日期
    @Column(name = "last_trouble")
    private String lastTrouble;

    // 危险性评估等级
    @Column(name = "risk_level")
    private String riskLevel;

    // 治疗情况
    @Column(name = "treat_flag")
    private String treatFlag;

    // 治疗医院名称
    @Column(name = "reat_hospital")
    private String reatHospital;

    // 实施住院治疗原因  ---多选项
    @Column(name = "inhospital_reason")
    private String inhospitalReason;

    // 接受康复机构名称
    @Column(name = "revice_union")
    private String reviceUnion;

    // 参与管理人员  ---多选项
    @Column(name = "join_manager")

    private String joinManager;

    // 帮扶情况  ---多选项
    @Column(name = "helpe_flag")
    private String helpeFlag;

    // 生效时间
    @Column(name = "eff_date", insertable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false,updatable = false)
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
    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
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

    public void copy(PsychosisPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(false));
    }
}