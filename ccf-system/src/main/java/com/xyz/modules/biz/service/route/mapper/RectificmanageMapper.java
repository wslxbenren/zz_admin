package com.xyz.modules.biz.service.route.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.modules.biz.service.route.dto.RectificmanageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RectificmanageMapper extends EntityMapper<RectificmanageDTO, Rectificmanage> {

}