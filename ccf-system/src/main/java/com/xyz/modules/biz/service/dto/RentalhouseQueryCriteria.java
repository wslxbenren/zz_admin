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
@ApiModel("实有人口->出租房屋基础信息")
public class RentalhouseQueryCriteria{
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
    @Query(type = Query.Type.EQUAL)
    private String houseCode;

    @Query(type = Query.Type.INNER_LIKE)
    private String houseName;

    @Query(type = Query.Type.INNER_LIKE)
    private String homeownerName;

    @Query(type = Query.Type.EQUAL)
    private String cardType;

    @Query(type = Query.Type.EQUAL)
    private String cardNo;

    @Query(type = Query.Type.EQUAL)
    private String hazardType;

    @Query(type = Query.Type.EQUAL)
    private String lesseeIdno;

    @Query(type = Query.Type.EQUAL)
    private String statusCd;

    @Query(type = Query.Type.INNER_LIKE)
    private String lesseeName;

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