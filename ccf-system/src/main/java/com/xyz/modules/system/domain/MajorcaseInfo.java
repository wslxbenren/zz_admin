package com.xyz.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Entity
@Data
@Table(name="biz_org_majorcase_info")
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
    private Timestamp occurDate;

    // 发生地:编码应符合GB/T2260
    @Column(name = "occur_addr")
    private String occurAddr;

    // 发生地详址
    @Column(name = "occur_addrdetail")
    private String occurAddrdetail;

    // 案（事）件分级:字典
    @Column(name = "case_grage")
    private String caseGrage;

    // 案（事）件类型:字典
    @Column(name = "case_type")
    private String caseType;

    // 案（事）件情况
    @Column(name = "case_info")
    private String caseInfo;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "update_time")
    private Timestamp updateTime;

    // 所属单位
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(MajorcaseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}