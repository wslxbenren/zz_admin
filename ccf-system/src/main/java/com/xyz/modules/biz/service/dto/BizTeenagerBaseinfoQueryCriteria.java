package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class BizTeenagerBaseinfoQueryCriteria{

    //时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    //字典项
    @Query(type = Query.Type.EQUAL)
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    private String partyFlag;

    @Query(type = Query.Type.EQUAL)
    private String nation;

    // 精确  其他
    @Query(type = Query.Type.EQUAL)
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    private String creator;

}