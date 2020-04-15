package com.xyz.modules.biz.service.route.impl;

import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.route.repo.RectificmanageRepository;
import com.xyz.modules.biz.service.route.RectificmanageService;
import com.xyz.modules.biz.service.route.dto.RectificmanageDTO;
import com.xyz.modules.biz.service.route.qo.RectificmanageQueryCriteria;
import com.xyz.modules.biz.service.route.mapper.RectificmanageMapper;
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
public class RectificmanageServiceImpl implements RectificmanageService {

    @Autowired
    private RectificmanageRepository RectificmanageRepository;

    @Autowired
    private RectificmanageMapper RectificmanageMapper;

    @Override
    @Transactional
    public Object queryAll(RectificmanageQueryCriteria criteria, Pageable pageable){
        Page<Rectificmanage> page = RectificmanageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(RectificmanageMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(RectificmanageQueryCriteria criteria){
        return RectificmanageMapper.toDto(RectificmanageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public RectificmanageDTO findById(String rectId) {
        Optional<Rectificmanage> Rectificmanage = RectificmanageRepository.findById(rectId);
        ValidationUtil.isNull(Rectificmanage,"Rectificmanage","rectId",rectId);
        return RectificmanageMapper.toDto(Rectificmanage.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RectificmanageDTO create(Rectificmanage resources) {
        resources.setRectId(IdUtil.simpleUUID()); 
        return RectificmanageMapper.toDto(RectificmanageRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rectificmanage resources) {
        Optional<Rectificmanage> optionalRectificmanage = RectificmanageRepository.findById(resources.getRectId());
        ValidationUtil.isNull( optionalRectificmanage,"Rectificmanage","id",resources.getRectId());
        Rectificmanage Rectificmanage = optionalRectificmanage.get();
        Rectificmanage.copy(resources);
        RectificmanageRepository.save(Rectificmanage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rectId) {
        RectificmanageRepository.deleteById(rectId);
    }
}