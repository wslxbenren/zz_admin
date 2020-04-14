package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "姓名")
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "公民身份证号")
    private String identityNum;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "性别")
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "帮扶人姓名")
    private String helperName;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "帮扶人联系方式")
    private String helperAddress;

    @Query
    @ApiModelProperty(value = "生日：格式[yyyy-MM-dd]")
    private List<String> dateBirth;

    @Query
    @ApiModelProperty(value = "民族")
    private String nation;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "籍贯")
    private String nativeInfo;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位，后续可用于权限管理")
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String modifier;
}