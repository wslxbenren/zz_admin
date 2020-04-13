package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class BizSecurHomicidebaseinfoQueryCriteria{

    // 时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> createTime;

    // 精确  其他
    @Query(type = Query.Type.EQUAL)
    private String caseName;

    @Query(type = Query.Type.INNER_LIKE)
    private String creator;


}