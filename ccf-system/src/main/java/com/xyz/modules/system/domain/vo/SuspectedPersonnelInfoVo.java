package com.xyz.modules.system.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SuspectedPersonnelInfoVo
 implements Serializable {
        // 人员编号
        private String peId;

        // 姓名
        private String peName;

        // 性别
        private String peSex;

        // 身份证号
        private String peIdno;

        // 年龄
        private String peAge;

        // 联系电话
        private String peMobile;

        // 常住地住址
        private String peAlwaddr;

        // 现在郑住址
        private String peAddr;

        // 是否途径武汉或湖北,1是0否
        private BigDecimal ifHubei;

        // 途径时间

        private Long wayTime;

        // 到达郑州时间

        private Long arriveTime;

        // 同行人员姓名：,分隔
        private String peerName;

        // 同行人关系说明
        private String peerInfo;

        // 同行人员联系方式
        private String peerMobile;

        // 同行人员身份证号
        private String peerIdno;

        // 交通方式
        private String transportMode;

        // 车次/班次/航班号/车牌号
        private String trainNo;

        // 途中是否停留1是0否
        private BigDecimal ifStay;

        // 停留地点说明
        private String stayaddr;

        // 居家隔离开始日期

        private Long stayTime;

        // 家人是否有病例或是否接触过病例说明
        private String contactInfo;

        // 身体状况说明
        private String physicalInfo;

        // 体温是否正常1正常0异常
        private BigDecimal ifOk;

        // 其他说明
        private String remark;

        // 采取措施（是否已隔离或者解除）
        private String measures;

        // 录入人
        private String entryName;

        // 录入时间

        private Long entryTime;

        // 录入单位
        private String entryUnit;

        // 是否安排排查人跟进1是0否
        private BigDecimal ifArrange;

        // 安排时间
        private Long arrTime;

        // 排查人
        private String screeningName;

        // 负责人
        private String headName;

        // 责任领导
        private String leaderName;

        // 更新时间
        private Long updateTime;

        private BigDecimal applyStatus;

        public SuspectedPersonnelInfo copy() throws IllegalAccessException {
            SuspectedPersonnelInfo s = new SuspectedPersonnelInfo();
            BeanUtil.copyProperties(this, s, CopyOptions.create().setIgnoreNullValue(true));
            return s;
        }
    }
