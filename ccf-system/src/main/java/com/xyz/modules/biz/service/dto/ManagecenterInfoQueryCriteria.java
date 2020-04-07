package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;

import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Data
public class ManagecenterInfoQueryCriteria{
    @Query
    private String addr;

    @Query
    private String grage;

    // 多字段模糊
    @Query(blurry = "username")
    private String blurry;



}