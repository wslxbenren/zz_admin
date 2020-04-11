package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
@ApiModel("特殊人群->艾滋病危险人群")
public class AidsPersonQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    @Query
    @ApiModelProperty(value = "")
    private String personName;

    @Query
    @ApiModelProperty(value = "")
    private String personSex;

    @Query
    @ApiModelProperty(value = "")
    private Timestamp dateBirth;

    @Query
    @ApiModelProperty(value = "")
    private String nation;

    @Query
    @ApiModelProperty(value = "")
    private String identityNum;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "")
    private String nativeInfo;

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