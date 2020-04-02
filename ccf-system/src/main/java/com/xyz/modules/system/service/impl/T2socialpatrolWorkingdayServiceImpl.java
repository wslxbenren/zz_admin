package com.xyz.modules.system.service.impl;

import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.system.domain.T2socialpatrolWorkingday;
import com.xyz.modules.system.repository.T2socialpatrolWorkingdayRepository;
import com.xyz.modules.system.service.T2socialpatrolWorkingdayService;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayDTO;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayQueryCriteria;
import com.xyz.modules.system.service.mapper.T2socialpatrolWorkingdayMapper;
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
public class T2socialpatrolWorkingdayServiceImpl implements T2socialpatrolWorkingdayService {

    @Autowired
    private T2socialpatrolWorkingdayRepository t2socialpatrolWorkingdayRepository;

    @Autowired
    private T2socialpatrolWorkingdayMapper t2socialpatrolWorkingdayMapper;

    @Override
    public Object queryAll(T2socialpatrolWorkingdayQueryCriteria criteria, Pageable pageable){
        Page<T2socialpatrolWorkingday> page = t2socialpatrolWorkingdayRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(t2socialpatrolWorkingdayMapper::toDto));
    }

    @Override
    public Object queryAll(T2socialpatrolWorkingdayQueryCriteria criteria){
        return t2socialpatrolWorkingdayMapper.toDto(t2socialpatrolWorkingdayRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public T2socialpatrolWorkingdayDTO findById(String id) {
        Optional<T2socialpatrolWorkingday> t2socialpatrolWorkingday = t2socialpatrolWorkingdayRepository.findById(id);
        ValidationUtil.isNull(t2socialpatrolWorkingday,"T2socialpatrolWorkingday","id",id);
        return t2socialpatrolWorkingdayMapper.toDto(t2socialpatrolWorkingday.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T2socialpatrolWorkingdayDTO create(T2socialpatrolWorkingday resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return t2socialpatrolWorkingdayMapper.toDto(t2socialpatrolWorkingdayRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T2socialpatrolWorkingday resources) {
        Optional<T2socialpatrolWorkingday> optionalT2socialpatrolWorkingday = t2socialpatrolWorkingdayRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalT2socialpatrolWorkingday,"T2socialpatrolWorkingday","id",resources.getId());
        T2socialpatrolWorkingday t2socialpatrolWorkingday = optionalT2socialpatrolWorkingday.get();
        t2socialpatrolWorkingday.copy(resources);
        t2socialpatrolWorkingdayRepository.save(t2socialpatrolWorkingday);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        t2socialpatrolWorkingdayRepository.deleteById(id);
    }
}