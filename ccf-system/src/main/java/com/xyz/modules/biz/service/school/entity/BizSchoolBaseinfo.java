package com.xyz.modules.biz.service.school.entity;

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
 * @author 邢家华
 * @date 2020-04-10
 */
@Entity
@Data
@Table(name="biz_school_baseinfo")
public class BizSchoolBaseinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "base_id")
    private String baseId;

    // 学校编码
    @Column(name = "school_code")
    private String schoolCode;

    // 学校名称
    @Column(name = "school_name")
    private String schoolName;

    // 学校地址省市县编码
    @Column(name = "school_addrcode")
    private String schoolAddrcode;

    // 学校地址
    @Column(name = "school_addr")
    private String schoolAddr;

    // 学校办学类型
    @Column(name = "school_type")
    private String schoolType;

    // 学校所属主管教育行政部门
    @Column(name = "compet_dept")
    private String competDept;

    // 在校学生人数
    @Column(name = "school_num")
    private Integer schoolNum;

    // 校长姓名
    @Column(name = "headmaster_name")
    private String headmasterName;

    // 校长联系方式
    @Column(name = "headmaster_mobile")
    private String headmasterMobile;

    // 分管安全保卫责任人姓名
    @Column(name = "guardcharge_name")
    private String guardchargeName;

    // 分管安全保卫责任人联系方式
    @Column(name = "guardcharge_mobile")
    private String guardchargeMobile;

    // 安全保卫责任人姓名
    @Column(name = "guardrespon_name")
    private String guardresponName;

    // 安全保卫责任人联系方式
    @Column(name = "guardrespon_mobile")
    private String guardresponMobile;

    // 治安责任人姓名
    @Column(name = "security_name")
    private String securityName;

    // 治安责任人联系方式
    @Column(name = "security_mobile")
    private String securityMobile;

    // 安全保卫人数
    @Column(name = "guard_num")
    private Integer guardNum;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
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
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable=false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(BizSchoolBaseinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}