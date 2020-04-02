package com.xyz.modules.system.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.T5keypersonScreeningwork;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface T5keypersonScreeningworkMapper extends EntityMapper<T5keypersonScreeningworkDTO, T5keypersonScreeningwork> {

}