package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 实有人口-流动人口
 * @author lx
 * @date 2020-04-08
 */
@Data
@ApiModel("实有人口-流动人口")
public class FloatpeopleQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;


    @Query(type = Query.Type.BETWEEN)
    private List<String> dateBirth;

    @Query(type = Query.Type.BETWEEN)
    private List<String> regisDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> expiryDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> effDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> expDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> operDate;

    @Query(type = Query.Type.EQUAL)
    private String nation;

    @Query(type = Query.Type.EQUAL)
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    private String nativeInfo;

    @Query(type = Query.Type.EQUAL)
    private String marriageFlag;

    @Query(type = Query.Type.EQUAL)
    private String partyFlag;

    @Query(type = Query.Type.EQUAL)
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    private String faithType;

    @Query(type = Query.Type.EQUAL)
    private String vocationCode;

    @Query(type = Query.Type.EQUAL)
    private String registeredPlace;


    @Query(type = Query.Type.INNER_LIKE)
    private String serviceAddr;

    @Query(type = Query.Type.INNER_LIKE)
    private String usedName;

    @Query(type = Query.Type.INNER_LIKE)
    private String personName;

    private String identityNum;
    // 多字段模糊
    @Query(blurry = "personName,usedName")
    private String blurry;

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