package com.xyz.modules.system.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.T1beginepidemicPrevention;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface T1beginepidemicPreventionMapper extends EntityMapper<T1beginepidemicPreventionDTO, T1beginepidemicPrevention> {

}