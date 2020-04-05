package com.xyz.modules.system.service.mapper;

import EntityMapper;
import com.xyz.modules.system.domain.ManageleadresponsInfo;
import com.xyz.modules.system.service.dto.ManageleadresponsInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageleadresponsInfoMapper extends EntityMapper<ManageleadresponsInfoDTO, ManageleadresponsInfo> {

}