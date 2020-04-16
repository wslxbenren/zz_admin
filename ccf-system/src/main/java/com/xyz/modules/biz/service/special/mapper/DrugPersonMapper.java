package com.xyz.modules.biz.service.special.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.special.entity.DrugPerson;
import com.xyz.modules.biz.service.special.dto.DrugPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DrugPersonMapper extends EntityMapper<DrugPersonDTO, DrugPerson> {

}