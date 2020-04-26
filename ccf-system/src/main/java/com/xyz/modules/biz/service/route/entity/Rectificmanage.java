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
 * 线路专项整治管理信息表
 */
@Entity
@Data
@Table(name="biz_route_rectificmanage")
public class Rectificmanage implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "rect_id")
    private String rectId;

    // 跟线路基本表主键关联
    @Column(name = "con_id")
    private String conId;

    // 线路名称
    @Column(name = "route_name")
    private String routeName;

    // 行动类型
    @Column(name = "action_type")
    private String actionType;

    // 行动主题
    @Column(name = "action_theme")
    private String actionTheme;

    // 行动地
    @Column(name = "action_addr")
    private String actionAddr;

    // 行动日期
    @Column(name = "action_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp actionTime;

    // 出动人数
    @Column(name = "out_num")
    private Integer outNum;

    // 工作指标
    @Column(name = "work_indic")
    private String workIndic;

    // 工作内容
    @Column(name = "work_info")
    private String workInfo;

    // 责任人
    @Column(name = "respon_name")
    private String responName;

    // 责任单位
    @Column(name = "respon_unit")
    private String responUnit;

    // 协办单位
    @Column(name = "assist_unit")
    private String assistUnit;

    // 出动人员
    @Column(name = "assist_outnum")
    private Integer assistOutnum;

    // 整治成效
    @Column(name = "reguresults")
    private String reguresults;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    @UpdateTimestamp
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Rectificmanage source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}