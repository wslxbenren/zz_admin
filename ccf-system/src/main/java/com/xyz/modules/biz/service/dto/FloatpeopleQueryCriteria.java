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
public class FloatpeopleQueryCriteria{
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

    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

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

    private String creator;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;
}