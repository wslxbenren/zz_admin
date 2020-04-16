package com.xyz.modules.biz.service.school.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.school.entity.BizSchoolBaseinfo;
import com.xyz.modules.biz.service.school.dto.BizSchoolBaseinfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BizSchoolBaseinfoMapper extends EntityMapper<BizSchoolBaseinfoDTO, BizSchoolBaseinfo> {

}