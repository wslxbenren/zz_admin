package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.domain.T5keypersonScreeningwork;
import com.xyz.modules.system.repository.T5keypersonScreeningworkRepository;
import com.xyz.modules.system.service.T5keypersonScreeningworkService;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkDTO;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkQueryCriteria;
import com.xyz.modules.system.service.mapper.T5keypersonScreeningworkMapper;
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
import com.xyz.utils.ValidationUtil;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class T5keypersonScreeningworkServiceImpl implements T5keypersonScreeningworkService {

    @Autowired
    private T5keypersonScreeningworkRepository t5keypersonScreeningworkRepository;

    @Autowired
    private T5keypersonScreeningworkMapper t5keypersonScreeningworkMapper;

    @Override
    public Object queryAll(T5keypersonScreeningworkQueryCriteria criteria, Pageable pageable){
        Page<T5keypersonScreeningwork> page = t5keypersonScreeningworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(t5keypersonScreeningworkMapper::toDto));
    }

    @Override
    public Object queryAll(T5keypersonScreeningworkQueryCriteria criteria){
        return t5keypersonScreeningworkMapper.toDto(t5keypersonScreeningworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public T5keypersonScreeningworkDTO findById(String id) {
        Optional<T5keypersonScreeningwork> t5keypersonScreeningwork = t5keypersonScreeningworkRepository.findById(id);
        ValidationUtil.isNull(t5keypersonScreeningwork,"T5keypersonScreeningwork","id",id);
        return t5keypersonScreeningworkMapper.toDto(t5keypersonScreeningwork.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T5keypersonScreeningworkDTO create(T5keypersonScreeningwork resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return t5keypersonScreeningworkMapper.toDto(t5keypersonScreeningworkRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T5keypersonScreeningwork resources) {
        Optional<T5keypersonScreeningwork> optionalT5keypersonScreeningwork = t5keypersonScreeningworkRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalT5keypersonScreeningwork,"T5keypersonScreeningwork","id",resources.getId());
        T5keypersonScreeningwork t5keypersonScreeningwork = optionalT5keypersonScreeningwork.get();
        t5keypersonScreeningwork.copy(resources);
        t5keypersonScreeningworkRepository.save(t5keypersonScreeningwork);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        t5keypersonScreeningworkRepository.deleteById(id);
    }
}