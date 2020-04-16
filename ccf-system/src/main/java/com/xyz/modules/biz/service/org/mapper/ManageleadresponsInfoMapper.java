package com.xyz.modules.biz.service.org.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.org.entity.ManageleadresponsInfo;
import com.xyz.modules.biz.service.org.dto.ManageleadresponsInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManageleadresponsInfoMapper extends EntityMapper<ManageleadresponsInfoDTO, ManageleadresponsInfo> {

}