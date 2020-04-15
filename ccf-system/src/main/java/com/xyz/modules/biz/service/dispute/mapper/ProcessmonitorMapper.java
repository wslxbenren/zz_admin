package com.xyz.modules.biz.service.dispute.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.dispute.entity.Processmonitor;
import com.xyz.modules.biz.service.dispute.dto.ProcessmonitorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProcessmonitorMapper extends EntityMapper<ProcessmonitorDTO, Processmonitor> {

}