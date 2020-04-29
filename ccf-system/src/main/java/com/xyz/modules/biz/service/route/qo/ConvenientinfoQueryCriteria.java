package com.xyz.modules.biz.service.route.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xjh
 * @date 2020-04-10
 */
@Data
@ApiModel("护路护线->护路护线基本信息表")
public class ConvenientinfoQueryCriteria{
//    // 时间
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> createTime;
//
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> operDate;

    // 精确
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "线路名称")
    private String routeName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "隶属单位负责人姓名")
    private String subordName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "隶属单位联系方式")
    private String subordunitPhone;


    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "管辖单位名称")
    private String jurisdunit;

    @Query(type = Query.Type.EQUAL)
    @JsonIgnore
    @ApiModelProperty(value = "线路类型")
    private List<String> routeType;

    @Query(type = Query.Type.EQUAL)
    @JsonIgnore
    @ApiModelProperty(value = "治安隐患等级")
    private List<String> secuhiddenLevel;

//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "创建人id")
//    private String creator;
//
//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "修改人id")
//    private String operName;
}