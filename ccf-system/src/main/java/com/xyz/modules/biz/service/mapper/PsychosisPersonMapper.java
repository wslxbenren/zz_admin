package com.xyz.modules.biz.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.domain.PsychosisPerson;
import com.xyz.modules.biz.service.dto.PsychosisPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PsychosisPersonMapper extends EntityMapper<PsychosisPersonDTO, PsychosisPerson> {

}