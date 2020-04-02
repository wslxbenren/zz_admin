package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 */
@Data
public class RoleQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,remark")
    private String blurry;
}
