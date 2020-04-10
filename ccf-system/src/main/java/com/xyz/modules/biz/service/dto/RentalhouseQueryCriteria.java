package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author lx
* @date 2020-04-08
*/
@Data
public class RentalhouseQueryCriteria{
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

    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    private String creator;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;
}