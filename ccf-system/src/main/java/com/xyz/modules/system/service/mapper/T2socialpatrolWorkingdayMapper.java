package com.xyz.modules.system.service.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.system.domain.T2socialpatrolWorkingday;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface T2socialpatrolWorkingdayMapper extends EntityMapper<T2socialpatrolWorkingdayDTO, T2socialpatrolWorkingday> {

}