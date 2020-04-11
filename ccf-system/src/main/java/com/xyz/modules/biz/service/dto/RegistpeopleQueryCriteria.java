package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lx
 * @date 2020-04-08
*/
@Data
@ApiModel("实有人口->户籍人口")
public class RegistpeopleQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    /**
     *  @Query(type = Query.Type.BETWEEN)
     *   @Query(type = Query.Type.EQUAL)
     *   @Query(type = Query.Type.IN)
     *    @Query(type = Query.Type.INNER_LIKE)
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    private String usedName;

    @Query(type = Query.Type.BETWEEN)
    private List<String> dateBirth;

    @Query(type = Query.Type.EQUAL)
    private String nation;

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

    @Query(type = Query.Type.EQUAL)
    private String householdId;

    @Query(type = Query.Type.EQUAL)
    private String househeadName;

    @Query(type = Query.Type.BETWEEN)
    private List<String> effDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> expDate;

    @Query(type = Query.Type.BETWEEN)
    private List<String> operDate;


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