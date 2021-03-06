package com.xyz.modules.biz.service.secur.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.secur.entity.Suspectinfo;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuspectinfoMapper extends EntityMapper<SuspectinfoDTO, Suspectinfo> {

}