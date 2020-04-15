package com.xyz.modules.biz.service.special.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.special.entity.BegPerson;
import com.xyz.modules.biz.service.special.dto.BegPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BegPersonMapper extends EntityMapper<BegPersonDTO, BegPerson> {

}