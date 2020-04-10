package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.CorrectPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.CorrectPersonRepository;
import com.xyz.modules.biz.service.CorrectPersonService;
import com.xyz.modules.biz.service.dto.CorrectPersonDTO;
import com.xyz.modules.biz.service.dto.CorrectPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.CorrectPersonMapper;
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
public class CorrectPersonServiceImpl implements CorrectPersonService {

    @Autowired
    private CorrectPersonRepository CorrectPersonRepository;

    @Autowired
    private CorrectPersonMapper CorrectPersonMapper;

    @Override
    public Object queryAll(CorrectPersonQueryCriteria criteria, Pageable pageable){
        Page<CorrectPerson> page = CorrectPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(CorrectPersonMapper::toDto));
    }

    @Override
    public Object queryAll(CorrectPersonQueryCriteria criteria){
        return CorrectPersonMapper.toDto(CorrectPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public CorrectPersonDTO findById(String correctId) {
        Optional<CorrectPerson> CorrectPerson = CorrectPersonRepository.findById(correctId);
        ValidationUtil.isNull(CorrectPerson,"CorrectPerson","correctId",correctId);
        return CorrectPersonMapper.toDto(CorrectPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CorrectPersonDTO create(CorrectPerson resources) {
        resources.setCorrectId(IdUtil.simpleUUID()); 
        if(CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        return CorrectPersonMapper.toDto(CorrectPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CorrectPerson resources) {
        Optional<CorrectPerson> optionalCorrectPerson = CorrectPersonRepository.findById(resources.getCorrectId());
        ValidationUtil.isNull( optionalCorrectPerson,"CorrectPerson","id",resources.getCorrectId());
        CorrectPerson CorrectPerson = optionalCorrectPerson.get();
        CorrectPerson correctPerson = CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(correctPerson != null && !correctPerson.getCorrectId().equals(CorrectPerson.getCorrectId())){
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        CorrectPerson.copy(resources);
        CorrectPersonRepository.save(CorrectPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String correctId) {
        CorrectPersonRepository.deleteById(correctId);
    }
}