package com.xyz.modules.biz.service.special.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Entity
@Data
@Table(name="biz_special_beg_person")
public class BegPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "beg_id")
    private String begId;

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

    // 现住地
    @Column(name = "residence")
    private String residence;

    // 现住地编码
    @Column(name = "residence_code")
    private String residenceCode;

    // 现住地详细地址
    @Column(name = "residence_address")
    private String residenceAddress;

    // 所属类别
    @Column(name = "belong_type")
    private String belongType;

    // 是否属于留守儿童
    @Column(name = "leftover_child")
    private String leftoverChild;

    // 学习状况
    @Column(name = "study_comment")
    private String studyComment;

    // 未入学原因
    @Column(name = "unstudy_reason")
    private String unstudyReason;

    // 是否享受低保补助
    @Column(name = "is_ensure")
    private String isEnsure;

    // 辍学原因
    @Column(name = "stopstudy_reason")
    private String stopstudyReason;

    // 辍学其他原因
    @Column(name = "stopstudy_other")
    private String stopstudyOther;

    // 是否有犯罪史
    @Column(name = "is_pedigree")
    private String isPedigree;

    // 就业情况
    @Column(name = "work_comment")
    private String workComment;

    // 就业其他情况
    @Column(name = "work_other")
    private String workOther;

    // 是否有不良行为
    @Column(name = "bad_behaviour")
    private String badBehaviour;

    // 不良行为其他情况
    @Column(name = "behaviour_other")
    private String behaviourOther;

    // 未就业原因
    @Column(name = "unwork_reason")
    private String unworkReason;

    // 未就业其他原因
    @Column(name = "unwork_other")
    private String unworkOther;

    // 监护情况
    @Column(name = "guard_flag")
    private String guardFlag;

    // 监护情况原因
    @Column(name = "guard_reason")
    private String guardReason;

    // 其他需要说明问题
    @Column(name = "other_comments")
    private String otherComments;

    // 家庭主要经济来源
    @Column(name = "source_income")
    private String sourceIncome;

    // 其他经济来源
    @Column(name = "source_other")
    private String sourceOther;

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
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",updatable=false)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(BegPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}