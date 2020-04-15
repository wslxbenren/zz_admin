package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.secur.entity.BizSecurLogistics;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.secur.repo.BizSecurLogisticsRepository;
import com.xyz.modules.biz.service.secur.BizSecurLogisticsService;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsDTO;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.BizSecurLogisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：社会治安管理/寄递物流安全信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizSecurLogisticsServiceImpl implements BizSecurLogisticsService {

    @Autowired
    private BizSecurLogisticsRepository bizSecurLogisticsRepository;

    @Autowired
    private BizSecurLogisticsMapper bizSecurLogisticsMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/寄递物流安全信息--开始");
        Page<BizSecurLogistics> page = bizSecurLogisticsRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizSecurLogisticsDTO> bizSecurLogisticsDTOList = bizSecurLogisticsMapper.toDto(page.getContent());
        for (BizSecurLogisticsDTO f:bizSecurLogisticsDTOList){
            String dd = dictDetailService.transDict(DictEnum.DJZCLX.getDistName(), f.getRegisType());
            f.setRegisTypeStr(dd == null ? "无数据" : dd);// 登记注册类型
            dd = dictDetailService.transDict(DictEnum.KGQK.getDistName(), f.getHoldings());
            f.setHoldingsStr(dd == null ? "无数据" : dd);//控股情况
            dd = dictDetailService.transDict(DictEnum.JYFW.getDistName(), f.getBusinessScope());
            f.setBusinessScopeStr(dd == null ? "无数据" : dd);//经营范围
            dd = dictDetailService.transDict(DictEnum.QYLX.getDistName(), f.getEntityType());
            f.setEntityTypeStr(dd == null ? "无数据" : dd);//企业类型


            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", bizSecurLogisticsDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria){
        return bizSecurLogisticsMapper.toDto(bizSecurLogisticsRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public BizSecurLogisticsDTO findById(String logisId) {
        log.info("详情社会治安管理/寄递物流安全信息--开始");
        if (StringUtils.isBlank(logisId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurLogistics> bizSecurLogistics = bizSecurLogisticsRepository.findById(logisId);
        ValidationUtil.isNull(bizSecurLogistics,"BizSecurLogistics","logisId",logisId);
        return bizSecurLogisticsMapper.toDto(bizSecurLogistics.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurLogisticsDTO create(BizSecurLogistics resources) {
        log.info("新增社会治安管理/寄递物流安全信息--开始");
        resources.setLogisId(IdUtil.simpleUUID()); 
        return bizSecurLogisticsMapper.toDto(bizSecurLogisticsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurLogistics resources) {
        log.info("修改社会治安管理/寄递物流安全信息--开始");
        if (StringUtils.isBlank(resources.getLogisId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurLogistics> optionalBizSecurLogistics = bizSecurLogisticsRepository.findById(resources.getLogisId());
        ValidationUtil.isNull( optionalBizSecurLogistics,"BizSecurLogistics","id",resources.getLogisId());
        BizSecurLogistics bizSecurLogistics = optionalBizSecurLogistics.get();
        bizSecurLogistics.copy(resources);
        bizSecurLogisticsRepository.save(bizSecurLogistics);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String logisId) {
        log.info("删除社会治安管理/寄递物流安全信息--开始");
        if (StringUtils.isBlank(logisId)){
            throw new BadRequestException("主键ID不能为空");
        }
        bizSecurLogisticsRepository.deleteById(logisId);
    }
}