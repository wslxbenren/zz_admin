package com.xyz.modules.biz.service.actual.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.actual.entity.Foreigners;
import com.xyz.modules.biz.service.actual.dto.ForeignersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ForeignersMapper extends EntityMapper<ForeignersDTO, Foreigners> {

}