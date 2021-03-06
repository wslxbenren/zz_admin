package com.xyz.modules.biz.service.organ.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.organ.entity.Socialorgan;
import com.xyz.modules.biz.service.organ.dto.SocialorganDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SocialorganMapper extends EntityMapper<SocialorganDTO, Socialorgan> {

}