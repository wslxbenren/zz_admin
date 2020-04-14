package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：重点青少年/重点青少年基本信息
 */
@Entity
@Data
@Table(name="biz_teenager_baseinfo")
@DynamicUpdate
public class BizTeenagerBaseinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "teen_id")
    private String teenId;

    // 身份证号
    @Column(name = "identity_num",unique = true)
    private String identityNum;

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

    // 籍贯
    @Column(name = "native_info")
    private String nativeInfo;

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

    // 服务处所
    @Column(name = "service_addr")
    private String serviceAddr;

    // 联系方式
    @Column(name = "contact")
    private String contact;

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

    // 人员类型
    @Column(name = "people_type")
    private String peopleType;

    // 家庭情况
    @Column(name = "home_situ")
    private String homeSitu;

    // 监护人公民身份号码
    @Column(name = "guardian_idno")
    private String guardianIdno;

    // 监护人姓名
    @Column(name = "guardian_name")
    private String guardianName;

    // 与监护人关系
    @Column(name = "guardian_rela")
    private String guardianRela;

    // 监护人联系方式
    @Column(name = "guardian_mobile")
    private String guardianMobile;

    // 监护人居住详址
    @Column(name = "guardian_addr")
    private String guardianAddr;

    // 是否违法犯罪:1是0否
    @Column(name = "if_illegal")
    private Integer ifIllegal;

    // 违法犯罪情况
    @Column(name = "illegal_situ")
    private String illegalSitu;

    // 帮扶人姓名
    @Column(name = "helpe_name")
    private String helpeName;

    // 帮扶人联系方式
    @Column(name = "helpe_mobile")
    private String helpeMobile;

    // 帮扶手段
    @Column(name = "helpe_method")
    private String helpeMethod;

    // 帮扶情况
    @Column(name = "helpe_comment")
    private String helpeComment;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable=false)
    @CreationTimestamp
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

    // 监护人居住地址的省市县编码
    @Column(name = "guardian_addrcode")
    private String guardianAddrcode;

    public void copy(BizTeenagerBaseinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}