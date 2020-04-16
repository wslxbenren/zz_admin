package com.xyz.modules.biz.service.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
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
* @author dadovicn
* @date 2020-04-05
 * 功能模块 ： 综治组织/重大案件事件
*/
@Entity
@Data
@Table(name="biz_org_majorcase_info")
@DynamicUpdate
public class MajorcaseInfo implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 案（事）件编码
    @Column(name = "case_code")
    private String caseCode;

    // 案（事）件名称
    @Column(name = "case_name")
    private String caseName;

    // 发生日期:格式为“YYYYMMDD”
    @Column(name = "occur_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp occurDate;

    // 发生地:编码应符合GB/T2260
    @Column(name = "occur_addr")
    private String occurAddr;

    // 发生地详址
    @Column(name = "occur_addrdetail")
    private String occurAddrdetail;

    // 案（事）件分级:字典
    @Dict(DictEnum.AJFJ)
    @Column(name = "case_grage")
    private String caseGrage;

    // 案（事）件类型:字典
    @Dict(DictEnum.AJLX)
    @Column(name = "case_type")
    private String caseType;

    // 案（事）件情况
    @Column(name = "case_info")
    private String caseInfo;

    // 创建人
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",updatable=false)
    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp createTime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "update_time")
    @UpdateTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp updateTime;

    // 所属单位
    @Column(name = "unit_code")
    private String unitCode;

    // 生效时间
    @Column(name = "eff_date",insertable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",insertable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    private String statusCd;

    public void copy(MajorcaseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}