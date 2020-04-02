package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.domain.SuspectedPersonnelScreening;

import com.xyz.modules.system.repository.SuspectedPersonnelScreeningRepository;
import com.xyz.modules.system.service.SuspectedPersonnelScreeningService;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningDTO;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningQueryCriteria;
import com.xyz.modules.system.service.mapper.SuspectedPersonnelScreeningMapper;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuspectedPersonnelScreeningServiceImpl implements SuspectedPersonnelScreeningService {

    @Autowired
    private SuspectedPersonnelScreeningRepository suspectedPersonnelScreeningRepository;

    @Autowired
    private SuspectedPersonnelScreeningMapper suspectedPersonnelScreeningMapper;

    @Override
    public Object queryAll(SuspectedPersonnelScreeningQueryCriteria criteria, Pageable pageable){
        Page<SuspectedPersonnelScreening> page = suspectedPersonnelScreeningRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(suspectedPersonnelScreeningMapper::toDto));
    }

    @Override
    public Object queryAll(SuspectedPersonnelScreeningQueryCriteria criteria){
        return suspectedPersonnelScreeningMapper.toDto(suspectedPersonnelScreeningRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public SuspectedPersonnelScreeningDTO findById(String id) {
        Optional<SuspectedPersonnelScreening> suspectedPersonnelScreening = suspectedPersonnelScreeningRepository.findById(id);
        ValidationUtil.isNull(suspectedPersonnelScreening,"SuspectedPersonnelScreening","id",id);
        return suspectedPersonnelScreeningMapper.toDto(suspectedPersonnelScreening.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectedPersonnelScreeningDTO create(SuspectedPersonnelScreening resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return suspectedPersonnelScreeningMapper.toDto(suspectedPersonnelScreeningRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SuspectedPersonnelScreening resources) {
        Optional<SuspectedPersonnelScreening> optionalSuspectedPersonnelScreening = suspectedPersonnelScreeningRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSuspectedPersonnelScreening,"SuspectedPersonnelScreening","id",resources.getId());
        SuspectedPersonnelScreening suspectedPersonnelScreening = optionalSuspectedPersonnelScreening.get();
        suspectedPersonnelScreening.copy(resources);
        suspectedPersonnelScreeningRepository.save(suspectedPersonnelScreening);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        suspectedPersonnelScreeningRepository.deleteById(id);
    }
}