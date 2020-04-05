package com.xyz.modules.system.service.mapper;

import EntityMapper;
import com.xyz.modules.system.domain.ManagecenterInfo;
import com.xyz.modules.system.service.dto.ManagecenterInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagecenterInfoMapper extends EntityMapper<ManagecenterInfoDTO, ManagecenterInfo> {

}