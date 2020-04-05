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
@Table(name="biz_org_manageleadrespons_info")
public class ManageleadresponsInfo implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 被实施地区编码
    @Column(name = "Impledarea_code")
    private String impledareaCode;

    // 被实施地区
    @Column(name = "Impledarea_name")
    private String impledareaName;

    // 被实施地区层级:字典
    @Column(name = "area_grage")
    private String areaGrage;

    // 实施主体名称
    @Column(name = "implementer_name")
    private String implementerName;

    // 实施主体层级:字典
    @Column(name = "implementer_grage")
    private String implementerGrage;

    // 政策种类:字典
    @Column(name = "policy_type")
    private String policyType;

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

    public void copy(ManageleadresponsInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}