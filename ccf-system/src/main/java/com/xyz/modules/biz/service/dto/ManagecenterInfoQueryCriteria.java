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
public class ManagecenterInfoQueryCriteria{
    //时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    private List<String> updateTime;

    //字典等值
    @Query(type = Query.Type.EQUAL)
    private String addr;

    @Query(type = Query.Type.EQUAL)
    private String grage;

    @Query(type = Query.Type.EQUAL)
    private String centerCode;

    @Query(type = Query.Type.IN)
    private String deptId;

    @Query(type = Query.Type.INNER_LIKE)
    private String centerName;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    private String creator;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;

    // 多字段模糊
//    @Query(blurry = "username")
//    private String blurry;

}