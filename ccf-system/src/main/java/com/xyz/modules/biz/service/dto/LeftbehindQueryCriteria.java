package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Data
public class LeftbehindQueryCriteria{

    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    @Query(type = Query.Type.INNER_LIKE)
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    private String creator;

    @Query(type = Query.Type.EQUAL)
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    private String partyFlag;

    @Query(type = Query.Type.EQUAL)
    private String nation;


}