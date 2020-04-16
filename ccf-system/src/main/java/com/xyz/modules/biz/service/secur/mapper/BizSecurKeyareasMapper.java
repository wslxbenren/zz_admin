package com.xyz.modules.biz.service.secur.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.secur.entity.BizSecurKeyareas;
import com.xyz.modules.biz.service.secur.dto.BizSecurKeyareasDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BizSecurKeyareasMapper extends EntityMapper<BizSecurKeyareasDTO, BizSecurKeyareas> {

}