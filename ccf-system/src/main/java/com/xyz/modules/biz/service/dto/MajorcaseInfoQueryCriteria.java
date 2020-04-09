package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author xjh
* @date 2020-04-05
*/
@Data
public class MajorcaseInfoQueryCriteria{

    //sql 做了类型转换cast(majorcasei0_.create_time as char) 有时候查不出内容，语法流程都是正确的
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    //字典项
    @Query(type = Query.Type.EQUAL)
    private String occurAddr;

    @Query(type = Query.Type.EQUAL)
    private String caseGrage;

    @Query(type = Query.Type.EQUAL)
    private String caseType;

    //其他
    @Query(type = Query.Type.INNER_LIKE)
    private String creator;

    @Query(type = Query.Type.INNER_LIKE)
    private String modifier;

    @Query(type = Query.Type.INNER_LIKE)
    private String caseName;

}