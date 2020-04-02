package com.xyz.modules.system.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import com.xyz.modules.system.domain.SuspectedPersonnelScreening;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Data
public class SuspectedPersonnelScreeningVo implements Serializable {

    // 主键
    private String id;

    // 人员编号
    private String pId;

    // 排查人
    private String entryName;

    // 排查登记时间
    private Long screenTime;

    // 目前健康状况1正常2体温偏高有咳嗽3其他
    private BigDecimal ifHealth;

    // 目前所在城市地区
    private String pCity;

    // 隔离开始日期
    private Long stayTime;

    // 是否接触湖北人员
    private BigDecimal ifHubei;

    // 是否接触病例
    private BigDecimal ifContact;

    // 接触时间
    private Long contactTime;

    // 接触说明
    private String contactInfo;

    // 外出说明
    private String outInfo;

    // 外出时间
    private Long outTime;

    // 录入时间
    private Long entryTime;

    // 录入人单位
    private String entryUnit;

    // 更新时间
    private Long updateTime;

    private BigDecimal applyStatus;

    public void copy(SuspectedPersonnelScreeningVo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public SuspectedPersonnelScreening copy() throws IllegalAccessException {
        SuspectedPersonnelScreening s = new SuspectedPersonnelScreening();
        BeanUtil.copyProperties(this, s, CopyOptions.create().setIgnoreNullValue(true));
        return s;
    }
}