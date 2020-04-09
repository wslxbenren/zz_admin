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
public class RegistpeopleQueryCriteria{
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

    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;
}