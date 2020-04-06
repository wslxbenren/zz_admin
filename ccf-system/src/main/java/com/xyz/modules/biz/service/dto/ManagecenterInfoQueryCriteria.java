package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Data
public class ManagecenterInfoQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String username;


    @Query
    private String grage;

    @Query
    private String addr;

}