package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.xyz.annotation.Query;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class AidsPersonQueryCriteria{

    // 精确
    @Query
    private String personName;

    // 精确
    @Query
    private String personSex;

    // 精确
    @Query
    private Timestamp dateBirth;

    // 精确
    @Query
    private String nation;

    // 精确
    @Query
    private String identityNum;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String nativeInfo;

    // 精确
    @Query
    private Timestamp createTime;
}