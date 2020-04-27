package com.xyz.modules.biz.service.secur.entity;

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
import java.util.List;
import java.util.Set;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：社会治安管理/命案基本信息
 */
@Entity
@Data
@Table(name="biz_secur_homicidebaseinfo")
@DynamicUpdate
public class BizSecurHomicidebaseinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "case_id")
    private String caseId;

    // 案件名称
    @Column(name = "case_name")
    private String caseName;

    // 案件编号
    @Column(name = "case_code",unique = true)
    private String caseCode;

    // 案件发生开始日期
    @Column(name = "crime_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp crimeDate;

    // 侦查终结日期
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp endDate;

    // 简要情况
    @Column(name = "briefdescrip")
    private String briefdescrip;

    // 生效时间
    @Column(name = "eff_date",nullable = false,updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false,updatable = false)
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

    public void copy(BizSecurHomicidebaseinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }


//    //犯罪嫌疑人
//    @OneToMany(cascade=CascadeType.REFRESH , fetch = FetchType.EAGER, mappedBy="suspectinfo")
//    private Set<Suspectinfo> suspectinfoList;
//    //犯罪受害人
//    @OneToMany(cascade=CascadeType.REFRESH , fetch = FetchType.EAGER, mappedBy="victiminfo")
//    private Set<Victiminfo> victiminfos;

}