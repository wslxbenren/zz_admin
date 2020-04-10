package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.BizSecurLogistics;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizSecurLogisticsRepository;
import com.xyz.modules.biz.service.BizSecurLogisticsService;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsDTO;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSecurLogisticsMapper;
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
 * @author 刘鑫
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizSecurLogisticsServiceImpl implements BizSecurLogisticsService {

    @Autowired
    private BizSecurLogisticsRepository bizSecurLogisticsRepository;

    @Autowired
    private BizSecurLogisticsMapper bizSecurLogisticsMapper;

    @Override
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria, Pageable pageable){
        Page<BizSecurLogistics> page = bizSecurLogisticsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSecurLogisticsMapper::toDto));
    }

    @Override
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria){
        return bizSecurLogisticsMapper.toDto(bizSecurLogisticsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSecurLogisticsDTO findById(String logisId) {
        Optional<BizSecurLogistics> bizSecurLogistics = bizSecurLogisticsRepository.findById(logisId);
        ValidationUtil.isNull(bizSecurLogistics,"BizSecurLogistics","logisId",logisId);
        return bizSecurLogisticsMapper.toDto(bizSecurLogistics.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurLogisticsDTO create(BizSecurLogistics resources) {
        resources.setLogisId(IdUtil.simpleUUID()); 
        return bizSecurLogisticsMapper.toDto(bizSecurLogisticsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurLogistics resources) {
        Optional<BizSecurLogistics> optionalBizSecurLogistics = bizSecurLogisticsRepository.findById(resources.getLogisId());
        ValidationUtil.isNull( optionalBizSecurLogistics,"BizSecurLogistics","id",resources.getLogisId());
        BizSecurLogistics bizSecurLogistics = optionalBizSecurLogistics.get();
        bizSecurLogistics.copy(resources);
        bizSecurLogisticsRepository.save(bizSecurLogistics);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String logisId) {
        bizSecurLogisticsRepository.deleteById(logisId);
    }
}