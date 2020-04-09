package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author xjh
* @date 2020-04-08
*/
@Data
public class ForeignersQueryCriteria{

    //时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    //其他
    @Query(type = Query.Type.INNER_LIKE)
    private String firstname;

    @Query(type = Query.Type.INNER_LIKE)
    private String chinesename;

    @Query(type = Query.Type.INNER_LIKE)
    private String vocation;

    @Query(type = Query.Type.INNER_LIKE)
    private String creator;

    //字典项
    @Query(type = Query.Type.EQUAL)
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    private String country;

    @Query(type = Query.Type.EQUAL)
    private String vocationCode;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;
}