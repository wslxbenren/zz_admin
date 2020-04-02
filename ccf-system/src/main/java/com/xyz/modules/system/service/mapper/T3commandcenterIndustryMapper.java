package com.xyz.modules.system.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.T3commandcenterIndustry;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface T3commandcenterIndustryMapper extends EntityMapper<T3commandcenterIndustryDTO, T3commandcenterIndustry> {

}