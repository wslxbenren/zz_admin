package com.xyz.modules.biz.service.teenager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
@ApiModel("重点青少年->重点青少年")
public class BizTeenagerBaseinfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> operDate;

    // 原型查询条件
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "姓名")
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "公民身份证号")
    private String identityNum;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "联系方式")
    private String contact;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "性别")
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "监护人姓名")
    private String guardianName;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "监护人联系方式")
    private String guardianMobile;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位")
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String operName;
}