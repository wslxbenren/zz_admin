package com.xyz.modules.system.service.mapper;


import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspectedPersonnelInfoMapper extends EntityMapper<SuspectedPersonnelInfoDTO, SuspectedPersonnelInfo> {

}