package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Data
public class ManageleadresponsInfoQueryCriteria{

    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    @Query(type = Query.Type.EQUAL)
    private String policyType;

    @Query(type = Query.Type.EQUAL)
    private String implementerGrage;

    @Query(type = Query.Type.INNER_LIKE)
    private String creator;

    @Query(type = Query.Type.INNER_LIKE)
    private String modifier;



}