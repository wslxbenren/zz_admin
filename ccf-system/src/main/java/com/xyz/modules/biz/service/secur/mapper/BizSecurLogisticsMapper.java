package com.xyz.modules.biz.service.secur.mapper;

import com.xyz.mapper.EntityMapper;
import com.xyz.modules.biz.service.secur.entity.BizSecurLogistics;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BizSecurLogisticsMapper extends EntityMapper<BizSecurLogisticsDTO, BizSecurLogistics> {

}