package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Entity
@Data
@Table(name="biz_org_buildhead_info")
public class BuildheadInfo implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 小区（单位）编码
    @Column(name = "village_code")
    private String villageCode;

    // 小区（单位）名称
    @Column(name = "village_name")
    private String villageName;

    // 楼栋名称
    @Column(name = "build_name")
    private String buildName;

    // 建筑面积（平方米）
    @Column(name = "build_area")
    private Double buildArea;

    // 层数
    @Column(name = "layer_num")
    private Integer layerNum;

    // 单元数
    @Column(name = "unit_num")
    private Integer unitNum;

    // 楼栋户数
    @Column(name = "households_num")
    private Integer householdsNum;

    // 楼栋人数
    @Column(name = "people_num")
    private Integer peopleNum;

    // 楼栋长姓名
    @Column(name = "head_name")
    private String headName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "sex")
    @Dict(DictEnum.XING_BIE)
    private String sex;

    // 民族:编码应符合GB/T3304
    @Column(name = "national")
    @Dict(DictEnum.MIN_ZU)
    private String national;

    // 政治面貌:编码应符合GB/T4762
    @Column(name = "political_status")
    @Dict(DictEnum.ZZMM)
    private String politicalStatus;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "birth")
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp birth;

    // 学历:编码应符合GB/T4658
    @Dict(DictEnum.XUE_LI)
    @Column(name = "education_bg")
    private String educationBg;

    // 手机号码
    @Column(name = "mobile")
    private String mobile;

    // 固定电话
    @Column(name = "fixed_phone")
    private String fixedPhone;

    // 所在地:编码应符合GB/T2260
    @Column(name = "addr")
    @Dict(DictEnum.ADDRESS)
    private String addr;

    // 所在地详址
    @Column(name = "addr_detail")
    private String addrDetail;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    // 所属单位
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(BuildheadInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }


}