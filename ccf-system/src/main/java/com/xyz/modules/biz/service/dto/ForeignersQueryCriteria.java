package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
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
    private List<String> updateTime;

    //其他
    @Query(type = Query.Type.INNER_LIKE)
    private String firstname;

    @Query(type = Query.Type.INNER_LIKE)
    private String chinesename;

    @Query(type = Query.Type.INNER_LIKE)
    private String vocation;

    //字典项
    @Query(type = Query.Type.EQUAL)
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    private String country;

    @Query(type = Query.Type.EQUAL)
    private String vocationCode;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String modifier;
}