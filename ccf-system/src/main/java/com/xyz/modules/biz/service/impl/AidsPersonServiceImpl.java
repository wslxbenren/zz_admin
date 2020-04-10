package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.AidsPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.AidsPersonRepository;
import com.xyz.modules.biz.service.AidsPersonService;
import com.xyz.modules.biz.service.dto.AidsPersonDTO;
import com.xyz.modules.biz.service.dto.AidsPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.AidsPersonMapper;
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
public class AidsPersonServiceImpl implements AidsPersonService {

    @Autowired
    private AidsPersonRepository AidsPersonRepository;

    @Autowired
    private AidsPersonMapper AidsPersonMapper;

    @Override
    public Object queryAll(AidsPersonQueryCriteria criteria, Pageable pageable){
        Page<AidsPerson> page = AidsPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(AidsPersonMapper::toDto));
    }

    @Override
    public Object queryAll(AidsPersonQueryCriteria criteria){
        return AidsPersonMapper.toDto(AidsPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AidsPersonDTO findById(String aidsId) {
        Optional<AidsPerson> AidsPerson = AidsPersonRepository.findById(aidsId);
        ValidationUtil.isNull(AidsPerson,"AidsPerson","aidsId",aidsId);
        return AidsPersonMapper.toDto(AidsPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AidsPersonDTO create(AidsPerson resources) {
        resources.setAidsId(IdUtil.simpleUUID()); 
        if(AidsPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(AidsPerson.class,"identity_num",resources.getIdentityNum());
        }
        return AidsPersonMapper.toDto(AidsPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AidsPerson resources) {
        Optional<AidsPerson> optionalAidsPerson = AidsPersonRepository.findById(resources.getAidsId());
        ValidationUtil.isNull( optionalAidsPerson,"AidsPerson","id",resources.getAidsId());
        AidsPerson AidsPerson = optionalAidsPerson.get();
//        AidsPerson1 = AidsPersonRepository.findByIdentityNum(resources.getIdentityNum());
//        if(AidsPerson1 != null && !AidsPerson1.getAidsId().equals(AidsPerson.getAidsId())){
//            throw new EntityExistException(AidsPerson.class,"identity_num",resources.getIdentityNum());
//        }
        AidsPerson.copy(resources);
        AidsPersonRepository.save(AidsPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String aidsId) {
        AidsPersonRepository.deleteById(aidsId);
    }
}