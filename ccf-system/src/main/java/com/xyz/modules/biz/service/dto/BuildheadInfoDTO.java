package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author lx
* @date 2020-04-06
* 综治组织及总之业务->楼栋长信息
*/
@Data
public class BuildheadInfoDTO implements Serializable {

    // 主键
    private String id;

    // 小区（单位）编码
    private String villageCode;

    // 小区（单位）名称
    private String villageName;

    // 楼栋名称
    private String buildName;

    // 建筑面积（平方米）
    private Double buildArea;

    // 层数
    private Integer layerNum;

    // 单元数
    private Integer unitNum;

    // 楼栋户数
    private Integer householdsNum;

    // 楼栋人数
    private Integer peopleNum;

    // 楼栋长姓名
    private String headName;

    // 性别:编码应符合GB/T2261.1
    private String sex;

    // 性别:翻译后值
    private String sexStr;

    // 民族:编码应符合GB/T3304
    private String national;
    // 民族:翻译后值
    private String nationalStr;

    // 政治面貌:编码应符合GB/T4762
    private String politicalStatus;
    // 政治面貌:翻译后值
    private String politicalStatusStr;

    // 出生日期:格式为“YYYYMMDD”
    private Timestamp birth;

    // 学历:编码应符合GB/T4658
    private String educationBg;
    // 学历:翻译后值
    private String educationBgStr;

    // 手机号码
    private String mobile;

    // 固定电话
    private String fixedPhone;

    // 所在地:编码应符合GB/T2260
    private String addr;

    // 所在地详址
    private String addrDetail;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

    // 创建人
    private String creator;

    // 创建时间
    private Timestamp createTime;

    // 修改人
    private String modifier;

    // 修改时间
    private Timestamp updateTime;

    // 所属单位
    private String unitCode;
}