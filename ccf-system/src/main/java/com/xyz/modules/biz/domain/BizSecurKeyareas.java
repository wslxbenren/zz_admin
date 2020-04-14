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
 * 功能模块：社会治安管理/重点地区排查整治信息
 */
@Entity
@Data
@Table(name="biz_secur_keyareas")
@DynamicUpdate
public class BizSecurKeyareas implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "key_id")
    private String keyId;

    // 治安重点地区
    @Column(name = "security_karea")
    private String securityKarea;

    // 治安突出问题
    @Column(name = "outproblem")
    private String outproblem;

    // 涉及区域类型
    @Column(name = "area_type")
    private String areaType;

    // 涉及区域范围
    @Column(name = "area_scope")
    private String areaScope;

    // 整治牵头单位
    @Column(name = "leadunit_code")
    private String leadunitCode;

    // 整治参与单位
    @Column(name = "partunit_code")
    private String partunitCode;

    // 整治牵头单位负责人姓名
    @Column(name = "leadunit_pername")
    private String leadunitPername;

    // 整治牵头单位负责人联系方式
    @Column(name = "leadunit_permobile")
    private String leadunitPermobile;

    // 整改时限
    @Column(name = "limit_time")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp limitTime;

    // 整治期间破获刑事案件数
    @Column(name = "criminalcase_num")
    private String criminalcaseNum;

    // 整治期间查处治安案件数
    @Column(name = "securitycase_num")
    private String securitycaseNum;

    // 整治情况
    @Column(name = "regulation")
    private String regulation;

    // 效果评估
    @Column(name = "evaluation")
    private String evaluation;

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

    public void copy(BizSecurKeyareas source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}