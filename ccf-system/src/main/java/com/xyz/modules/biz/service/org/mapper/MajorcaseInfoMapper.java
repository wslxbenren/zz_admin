package com.xyz.modules.biz.service.org.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import com.xyz.modules.biz.service.org.dto.MajorcaseInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MajorcaseInfoMapper extends EntityMapper<MajorcaseInfoDTO, MajorcaseInfo> {

}