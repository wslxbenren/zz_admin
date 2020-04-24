package com.xyz.modules.biz.service.actual.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author xjh
* @date 2020-04-08
*/
@Data
@ApiModel(value = "实有人口-境外人员")
public class ForeignersQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> operDate;

    // 原型查询条件
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "外文姓")
    private String lastname;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "外文名")
    private String firstname;

    @ApiModelProperty(value = "国籍地区： 编码应符合GB/T2659")
    @Query(type = Query.Type.EQUAL)
    private String country;

    @ApiModelProperty(value = "联系方式")
    @Query(type = Query.Type.INNER_LIKE)
    private String contact;

    @ApiModelProperty(value = "证件号码")
    @Query(type = Query.Type.INNER_LIKE)
    private String cardNo;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "证件有效期")
    private List<String> validDate;

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