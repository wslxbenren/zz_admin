package com.xyz.modules.system.service.impl;

import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.system.domain.T3commandcenterIndustry;
import com.xyz.modules.system.repository.T3commandcenterIndustryRepository;
import com.xyz.modules.system.service.T3commandcenterIndustryService;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryDTO;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryQueryCriteria;
import com.xyz.modules.system.service.mapper.T3commandcenterIndustryMapper;
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
public class T3commandcenterIndustryServiceImpl implements T3commandcenterIndustryService {

    @Autowired
    private T3commandcenterIndustryRepository t3commandcenterIndustryRepository;

    @Autowired
    private T3commandcenterIndustryMapper t3commandcenterIndustryMapper;

    @Override
    public Object queryAll(T3commandcenterIndustryQueryCriteria criteria, Pageable pageable){
        Page<T3commandcenterIndustry> page = t3commandcenterIndustryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(t3commandcenterIndustryMapper::toDto));
    }

    @Override
    public Object queryAll(T3commandcenterIndustryQueryCriteria criteria){
        return t3commandcenterIndustryMapper.toDto(t3commandcenterIndustryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public T3commandcenterIndustryDTO findById(String id) {
        Optional<T3commandcenterIndustry> t3commandcenterIndustry = t3commandcenterIndustryRepository.findById(id);
        ValidationUtil.isNull(t3commandcenterIndustry,"T3commandcenterIndustry","id",id);
        return t3commandcenterIndustryMapper.toDto(t3commandcenterIndustry.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T3commandcenterIndustryDTO create(T3commandcenterIndustry resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return t3commandcenterIndustryMapper.toDto(t3commandcenterIndustryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T3commandcenterIndustry resources) {
        Optional<T3commandcenterIndustry> optionalT3commandcenterIndustry = t3commandcenterIndustryRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalT3commandcenterIndustry,"T3commandcenterIndustry","id",resources.getId());
        T3commandcenterIndustry t3commandcenterIndustry = optionalT3commandcenterIndustry.get();
        t3commandcenterIndustry.copy(resources);
        t3commandcenterIndustryRepository.save(t3commandcenterIndustry);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        t3commandcenterIndustryRepository.deleteById(id);
    }
}