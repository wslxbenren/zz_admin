package com.xyz.modules.biz.service.route.entity;

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
 * @author 刘鑫
 * @date 2020-04-10
 * 线路周边重点人员管理信息表线路周边重点人员管理信息表
 */
@Entity
@Data
@Table(name="biz_route_keypersoninfo")
public class Keypersoninfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "key_id")
    private String keyId;

    // 跟线路基本表主键关联
    @Column(name = "con_id")
    private String conId;

    // 线路名称
    @Column(name = "route_name")
    private String routeName;

    // 重点人员类型（符合特殊人群特征、废品收购站业主、流动废品收购人员等）
    @Column(name = "keyperson_type")
    private String keypersonType;

    // 公民身份号码
    @Column(name = "identity_num")
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp dateBirth;

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

    // 联系方式
    @Column(name = "mobile")
    private String mobile;

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

    // 是否关注
    @Column(name = "if_focus")
    private Integer ifFocus;

    // 危害程度
    @Column(name = "hazard_level")
    private String hazardLevel;

    // 危害说明
    @Column(name = "hazard_info")
    private String hazardInfo;

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
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time")
    @JsonIgnore
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Keypersoninfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}