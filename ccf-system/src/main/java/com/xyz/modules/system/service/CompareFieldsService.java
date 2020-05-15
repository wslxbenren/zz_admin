package com.xyz.modules.system.service;

import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.system.util.annotation.Dict;

import java.util.List;
import java.util.Map;

/**
 * @author lx
 * @version 1.0
 * @date 2020/5/15 11:24
 */
public interface CompareFieldsService {

    String compareModifyRecords(Object obj1, Object obj2, String[] ignoreArr);


}
