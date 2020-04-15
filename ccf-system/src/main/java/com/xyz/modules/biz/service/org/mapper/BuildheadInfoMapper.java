package com.xyz.modules.biz.service.org.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.dto.BuildheadInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author lx
* @date 2020-04-06
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuildheadInfoMapper extends EntityMapper<BuildheadInfoDTO, BuildheadInfo> {

}