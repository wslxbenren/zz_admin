package com.xyz.modules.biz.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.service.dto.RentalhouseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentalhouseMapper extends EntityMapper<RentalhouseDTO, Rentalhouse> {

}