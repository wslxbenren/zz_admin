package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.ReleasedPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ReleasedPersonRepository;
import com.xyz.modules.biz.service.ReleasedPersonService;
import com.xyz.modules.biz.service.dto.ReleasedPersonDTO;
import com.xyz.modules.biz.service.dto.ReleasedPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.ReleasedPersonMapper;
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
public class ReleasedPersonServiceImpl implements ReleasedPersonService {

    @Autowired
    private ReleasedPersonRepository ReleasedPersonRepository;

    @Autowired
    private ReleasedPersonMapper ReleasedPersonMapper;

    @Override
    public Object queryAll(ReleasedPersonQueryCriteria criteria, Pageable pageable){
        Page<ReleasedPerson> page = ReleasedPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ReleasedPersonMapper::toDto));
    }

    @Override
    public Object queryAll(ReleasedPersonQueryCriteria criteria){
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ReleasedPersonDTO findById(String releasedId) {
        Optional<ReleasedPerson> ReleasedPerson = ReleasedPersonRepository.findById(releasedId);
        ValidationUtil.isNull(ReleasedPerson,"ReleasedPerson","releasedId",releasedId);
        return ReleasedPersonMapper.toDto(ReleasedPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReleasedPersonDTO create(ReleasedPerson resources) {
        resources.setReleasedId(IdUtil.simpleUUID()); 
        if(ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReleasedPerson resources) {
        Optional<ReleasedPerson> optionalReleasedPerson = ReleasedPersonRepository.findById(resources.getReleasedId());
        ValidationUtil.isNull( optionalReleasedPerson,"ReleasedPerson","id",resources.getReleasedId());
        ReleasedPerson ReleasedPerson = optionalReleasedPerson.get();
        ReleasedPerson releasedPerson = ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(releasedPerson != null && !releasedPerson.getReleasedId().equals(ReleasedPerson.getReleasedId())){
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        ReleasedPerson.copy(resources);
        ReleasedPersonRepository.save(ReleasedPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String releasedId) {
        ReleasedPersonRepository.deleteById(releasedId);
    }
}