package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.BizSecurLogistics;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizSecurLogisticsRepository;
import com.xyz.modules.biz.service.BizSecurLogisticsService;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsDTO;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSecurLogisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizSecurLogisticsServiceImpl implements BizSecurLogisticsService {

    @Autowired
    private BizSecurLogisticsRepository bizSecurLogisticsRepository;

    @Autowired
    private BizSecurLogisticsMapper bizSecurLogisticsMapper;

    @Override
    @Transactional
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/寄递物流安全信息--开始");
        Page<BizSecurLogistics> page = bizSecurLogisticsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSecurLogisticsMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria){
        return bizSecurLogisticsMapper.toDto(bizSecurLogisticsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
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
        bizSecurLogisticsRepository.deleteById(logisId);
    }
}