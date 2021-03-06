package com.xyz.modules.biz.service.org.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author lx
* @date 2020-04-08
 * 楼栋长信息数据结构表
*/
@Entity
@Data
@Table(name="biz_org_buildhead_info")
@DynamicUpdate
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createTime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "update_time")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updateTime;

    // 所属单位
    @Column(name = "unit_code")
    private String unitCode;

    // 同步状态
    @Column(name = "status")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    private String statusCd;

    public void copy(BuildheadInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }


}
