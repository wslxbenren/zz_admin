package com.xyz.modules.system.service.impl;

import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.system.domain.T4fromwuhanScreeningwork;
import com.xyz.modules.system.repository.T4fromwuhanScreeningworkRepository;
import com.xyz.modules.system.service.T4fromwuhanScreeningworkService;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkDTO;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkQueryCriteria;
import com.xyz.modules.system.service.mapper.T4fromwuhanScreeningworkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class T4fromwuhanScreeningworkServiceImpl implements T4fromwuhanScreeningworkService {

    @Autowired
    private T4fromwuhanScreeningworkRepository t4fromwuhanScreeningworkRepository;

    @Autowired
    private T4fromwuhanScreeningworkMapper t4fromwuhanScreeningworkMapper;

    @Override
    public Object queryAll(T4fromwuhanScreeningworkQueryCriteria criteria, Pageable pageable){
        Page<T4fromwuhanScreeningwork> page = t4fromwuhanScreeningworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(t4fromwuhanScreeningworkMapper::toDto));
    }

    @Override
    public Object queryAll(T4fromwuhanScreeningworkQueryCriteria criteria){
        return t4fromwuhanScreeningworkMapper.toDto(t4fromwuhanScreeningworkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public T4fromwuhanScreeningworkDTO findById(String id) {
        Optional<T4fromwuhanScreeningwork> t4fromwuhanScreeningwork = t4fromwuhanScreeningworkRepository.findById(id);
        ValidationUtil.isNull(t4fromwuhanScreeningwork,"T4fromwuhanScreeningwork","id",id);
        return t4fromwuhanScreeningworkMapper.toDto(t4fromwuhanScreeningwork.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T4fromwuhanScreeningworkDTO create(T4fromwuhanScreeningwork resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return t4fromwuhanScreeningworkMapper.toDto(t4fromwuhanScreeningworkRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T4fromwuhanScreeningwork resources) {
        Optional<T4fromwuhanScreeningwork> optionalT4fromwuhanScreeningwork = t4fromwuhanScreeningworkRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalT4fromwuhanScreeningwork,"T4fromwuhanScreeningwork","id",resources.getId());
        T4fromwuhanScreeningwork t4fromwuhanScreeningwork = optionalT4fromwuhanScreeningwork.get();
        t4fromwuhanScreeningwork.copy(resources);
        t4fromwuhanScreeningworkRepository.save(t4fromwuhanScreeningwork);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        t4fromwuhanScreeningworkRepository.deleteById(id);
    }

    @Override
    public List selectGroupBy(String name) {
       List list= t4fromwuhanScreeningworkRepository.selectGropuBy();
        return list;
    }
}