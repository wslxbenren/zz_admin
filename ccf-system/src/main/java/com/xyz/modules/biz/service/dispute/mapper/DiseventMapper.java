package com.xyz.modules.biz.service.dispute.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.dispute.entity.Disevent;
import com.xyz.modules.biz.service.dispute.dto.DiseventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseventMapper extends EntityMapper<DiseventDTO, Disevent> {

}