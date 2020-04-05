package com.xyz.modules.biz.service.mapper;

import EntityMapper;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.service.dto.BuildheadInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuildheadInfoMapper extends EntityMapper<BuildheadInfoDTO, BuildheadInfo> {

}