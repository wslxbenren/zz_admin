package com.xyz.modules.system.service.mapper;


import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.SuspectedPersonnelScreening;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspectedPersonnelScreeningMapper extends EntityMapper<SuspectedPersonnelScreeningDTO, SuspectedPersonnelScreening> {

}