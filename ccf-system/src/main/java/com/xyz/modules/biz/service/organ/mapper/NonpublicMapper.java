package com.xyz.modules.biz.service.organ.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import com.xyz.modules.biz.service.organ.dto.NonpublicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NonpublicMapper extends EntityMapper<NonpublicDTO, Nonpublic> {

}