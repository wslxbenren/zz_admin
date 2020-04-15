package com.xyz.modules.biz.service.dispute.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.dispute.entity.Overseeinfo;
import com.xyz.modules.biz.service.dispute.dto.OverseeinfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OverseeinfoMapper extends EntityMapper<OverseeinfoDTO, Overseeinfo> {

}