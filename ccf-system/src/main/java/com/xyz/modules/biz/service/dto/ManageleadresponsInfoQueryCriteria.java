package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author xjh
* @date 2020-04-05
 * 功能模块 ： 综治组织/领导责任制
*/
@Data
@ApiModel("综治组织及综治业务->综治领导责任制")
public class ManageleadresponsInfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    // 原型查询条件
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "实施主体名称")
    private String implementerName;
    //字典项
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "政策种类:字典")
    private String policyType;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "实施主体层级:字典")
    private String implementerGrage;
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "被实施地区层级:字典")
    private String areaGrage;

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
    private String modifier;
}