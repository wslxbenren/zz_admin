package com.xyz.modules.biz.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.domain.Keypersoninfo;
import com.xyz.modules.biz.service.dto.KeypersoninfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KeypersoninfoMapper extends EntityMapper<KeypersoninfoDTO, Keypersoninfo> {

}