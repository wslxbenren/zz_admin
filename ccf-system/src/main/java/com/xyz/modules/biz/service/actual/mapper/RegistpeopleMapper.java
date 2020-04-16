package com.xyz.modules.biz.service.actual.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.actual.entity.Registpeople;
import com.xyz.modules.biz.service.actual.dto.RegistpeopleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author lx
* @date 2020-04-08
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistpeopleMapper extends EntityMapper<RegistpeopleDTO, Registpeople> {

}