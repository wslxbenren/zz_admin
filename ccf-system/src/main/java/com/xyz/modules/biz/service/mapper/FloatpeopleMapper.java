package com.xyz.modules.biz.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.domain.Floatpeople;
import com.xyz.modules.biz.service.dto.FloatpeopleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author lx
* @date 2020-04-09
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FloatpeopleMapper extends EntityMapper<FloatpeopleDTO, Floatpeople> {

}