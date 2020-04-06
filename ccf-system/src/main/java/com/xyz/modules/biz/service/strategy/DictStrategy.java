package com.xyz.modules.biz.service.strategy;

import com.xyz.modules.system.domain.Dict;

import java.util.List;

/**
 * todo 改为函数式接口
 * 此接口用定义一些常用的字典策略
 */
public interface DictStrategy {
    /**
     * 为每个实体创建字典集合
     * @return
     */
    List<Dict> buildDict();
}
