package com.xyz.modules.biz.domain;

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
 * @author xjh
 * @date 2020-04-10
 * 功能模块：护路护线/涉线、路案事件信息管理
 */
@Entity
@Data
@Table(name="biz_route_caseinfo")
@DynamicUpdate
public class Caseinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "case_id")
    private String caseId;

    // 跟线路基本表主键关联
    @Column(name = "con_id")
    private String conId;

    // 线路名称
    @Column(name = "route_name")
    private String routeName;

    // 案（事）件编号
    @Column(name = "case_code")
    private String caseCode;

    // 案（事）件类型
    @Column(name = "case_type")
    private String caseType;

    // 发生日期
    @Column(name = "happen_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp happenTime;

    // 发生地点省市县编码
    @Column(name = "happen_addrcode")
    private String happenAddrcode;

    // 发生地点
    @Column(name = "happen_addr")
    private String happenAddr;

    // 案（事）件性质
    @Column(name = "case_nature")
    private String caseNature;

    // 案（事）件情况
    @Column(name = "case_info")
    private String caseInfo;

    // 主犯（嫌疑人）证件代码
    @Column(name = "princcard_type")
    private String princcardType;

    // 主犯（嫌疑人）证件号码
    @Column(name = "princcard_code")
    private String princcardCode;

    // 主犯（嫌疑人）姓名
    @Column(name = "principal_name")
    private String principalName;

    // 是否破案
    @Column(name = "if_solve")
    private Integer ifSolve;

    // 作案人数
    @Column(name = "crime_num")
    private Integer crimeNum;

    // 在逃人数
    @Column(name = "onrun_num")
    private Integer onrunNum;

    // 抓捕人数
    @Column(name = "capture_num")
    private Integer captureNum;

    // 案件侦破情况
    @Column(name = "solve_info")
    private String solveInfo;

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

    public void copy(Caseinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}