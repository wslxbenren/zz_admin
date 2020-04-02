package com.xyz.modules.system.service.impl;

import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.system.domain.T1beginepidemicPrevention;
import com.xyz.modules.system.repository.T1beginepidemicPreventionRepository;
import com.xyz.modules.system.service.T1beginepidemicPreventionService;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionDTO;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionQueryCriteria;
import com.xyz.modules.system.service.mapper.T1beginepidemicPreventionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class T1beginepidemicPreventionServiceImpl implements T1beginepidemicPreventionService {

    @Autowired
    private T1beginepidemicPreventionRepository t1beginepidemicPreventionRepository;

    @Autowired
    private T1beginepidemicPreventionMapper t1beginepidemicPreventionMapper;

    @Override
    public Object queryAll(T1beginepidemicPreventionQueryCriteria criteria, Pageable pageable){
        Page<T1beginepidemicPrevention> page = t1beginepidemicPreventionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(t1beginepidemicPreventionMapper::toDto));
    }

    @Override
    public Object queryAll(T1beginepidemicPreventionQueryCriteria criteria){
        return t1beginepidemicPreventionMapper.toDto(t1beginepidemicPreventionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public T1beginepidemicPreventionDTO findById(String id) {
        Optional<T1beginepidemicPrevention> t1beginepidemicPrevention = t1beginepidemicPreventionRepository.findById(id);
        ValidationUtil.isNull(t1beginepidemicPrevention,"T1beginepidemicPrevention","id",id);
        return t1beginepidemicPreventionMapper.toDto(t1beginepidemicPrevention.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T1beginepidemicPreventionDTO create(T1beginepidemicPrevention resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return t1beginepidemicPreventionMapper.toDto(t1beginepidemicPreventionRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T1beginepidemicPrevention resources) {
        Optional<T1beginepidemicPrevention> optionalT1beginepidemicPrevention = t1beginepidemicPreventionRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalT1beginepidemicPrevention,"T1beginepidemicPrevention","id",resources.getId());
        T1beginepidemicPrevention t1beginepidemicPrevention = optionalT1beginepidemicPrevention.get();
        t1beginepidemicPrevention.copy(resources);
        t1beginepidemicPreventionRepository.save(t1beginepidemicPrevention);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        t1beginepidemicPreventionRepository.deleteById(id);
    }
}