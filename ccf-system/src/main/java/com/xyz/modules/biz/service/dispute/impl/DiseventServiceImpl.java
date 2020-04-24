package com.xyz.modules.biz.service.dispute.impl;

import com.xyz.modules.biz.service.dispute.entity.Disevent;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.dispute.repo.DiseventRepository;
import com.xyz.modules.biz.service.dispute.DiseventService;
import com.xyz.modules.biz.service.dispute.dto.DiseventDTO;
import com.xyz.modules.biz.service.dispute.qo.DiseventQueryCriteria;
import com.xyz.modules.biz.service.dispute.mapper.DiseventMapper;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DiseventServiceImpl implements DiseventService {

    @Autowired
    private DiseventRepository DiseventRepository;

    @Autowired
    private DiseventMapper DiseventMapper;

    @Override
    @Transactional
    public Object queryAll(DiseventQueryCriteria criteria, Pageable pageable){
        Page<Disevent> page = DiseventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(DiseventMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(DiseventQueryCriteria criteria){
        return DiseventMapper.toDto(DiseventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DiseventDTO findById(String eventId) {
        Optional<Disevent> Disevent = DiseventRepository.findById(eventId);
        ValidationUtil.isNull(Disevent,"Disevent","eventId",eventId);
        return DiseventMapper.toDto(Disevent.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DiseventDTO create(Disevent resources) {
        resources.setEventId(IdUtil.simpleUUID());
        return DiseventMapper.toDto(DiseventRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Disevent resources) {
        Optional<Disevent> optionalDisevent = DiseventRepository.findById(resources.getEventId());
        ValidationUtil.isNull( optionalDisevent,"Disevent","id",resources.getEventId());
        Disevent Disevent = optionalDisevent.get();
        Disevent.copy(resources);
        DiseventRepository.save(Disevent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String eventId) {
        DiseventRepository.deleteById(eventId);
    }
}