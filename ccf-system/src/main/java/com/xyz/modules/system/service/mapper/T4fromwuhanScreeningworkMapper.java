package com.xyz.modules.system.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.T4fromwuhanScreeningwork;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface T4fromwuhanScreeningworkMapper extends EntityMapper<T4fromwuhanScreeningworkDTO, T4fromwuhanScreeningwork> {

}