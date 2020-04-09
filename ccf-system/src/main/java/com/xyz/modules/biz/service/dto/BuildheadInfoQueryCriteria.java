package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.xyz.annotation.Query;

/**
* @author lx
* @date 2020-04-06
*/
@Data
public class BuildheadInfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    private List<String> updateTime;

    // 字典项
    @Query(type = Query.Type.EQUAL)
    private String sex;

    @Query(type = Query.Type.EQUAL)
    private String national;

    @Query(type = Query.Type.EQUAL)
    private String politicalStatus;

    @Query(type = Query.Type.EQUAL)
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    private String addr;
    // 注意这个不要加注解
    private String creator;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;

    // 其他
    @Query(type = Query.Type.INNER_LIKE)
    private String addrDetail;
}