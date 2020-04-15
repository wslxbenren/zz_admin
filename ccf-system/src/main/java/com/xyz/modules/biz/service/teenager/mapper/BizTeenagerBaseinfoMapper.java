package com.xyz.modules.biz.service.teenager.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.teenager.entity.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.teenager.dto.BizTeenagerBaseinfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BizTeenagerBaseinfoMapper extends EntityMapper<BizTeenagerBaseinfoDTO, BizTeenagerBaseinfo> {

}