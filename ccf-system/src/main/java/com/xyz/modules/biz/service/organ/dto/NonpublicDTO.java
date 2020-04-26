package com.xyz.modules.biz.service.organ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 非公有制经济组织及社会组织-> 非公有制经济组织
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class NonpublicDTO implements Serializable {

    // ID，uuid()赋值
    private String nonId;

    // 工商执照注册号（统一社会信用代码）
    private String creditCode;

    // 企业名称
    private String entityName;

    // 企业类别 字典
    private String entityType;
    private String entityTypeStr;

    // 企业地址省市县编码
    private String entityAddrcode;

    // 企业地址
    private String entityAddr;

    // 企业联系方式
    private String entityPhone;

    // 企业员工数
    private Integer entityNum;

    // 法定代表人证件代码
    private String legalcardType;

    // 法定代表人证件号码
    private String legalcardCode;

    // 法定代表人姓名
    private String legalpersonName;

    // 法定代表人联系方式
    private String legalpersonMobile;

    // 治保负责人姓名
    private String supervisorName;

    // 治保负责人联系方式
    private String supervisorMobile;

    // 是否危化企业
    private Integer ifDangerous;

    // 安全隐患类型 字典
    private String safetroubleType;
    private String safetroubleTypeStr;


    // 关注程度 字典
    private String attention;
    private String attentionStr;


    // 是否具备建立中共党组织条件:1是0否
    private Integer ifCondition;

    // 是否有中共党组织:1是0否
    private Integer ifOrgan;

    // 中共党员数量
    private Integer organNum;

    // 是否有工会:1是0否
    private Integer ifUnion;

    // 工会会员数量
    private Integer unionNum;

    // 是否有共青团组织:1是0否
    private Integer ifYouthleague;

    // 共青团团员数量
    private Integer youthleagueNum;

    // 是否有妇联组织:1是0否
    private Integer ifWomenfeder;

    // 妇女数量
    private Integer womenfederNum;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

    // 生效时间

    private Timestamp effDate;

    // 失效时间

    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效 字典
    private String statusCd;
    private String statusCdStr;


    // 操作人名称
    private String operName;

    // 操作时间

    private Timestamp operDate;

    // 创建人
    private String creator;

    // 创建时间

    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理 字典
    private String unitCode;
    private String unitCodeStr;
}