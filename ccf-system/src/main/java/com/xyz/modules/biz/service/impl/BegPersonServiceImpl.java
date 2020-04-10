package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.BegPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BegPersonRepository;
import com.xyz.modules.biz.service.BegPersonService;
import com.xyz.modules.biz.service.dto.BegPersonDTO;
import com.xyz.modules.biz.service.dto.BegPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.BegPersonMapper;
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
public class BegPersonServiceImpl implements BegPersonService {

    @Autowired
    private BegPersonRepository BegPersonRepository;

    @Autowired
    private BegPersonMapper BegPersonMapper;

    @Override
    public Object queryAll(BegPersonQueryCriteria criteria, Pageable pageable){
        Page<BegPerson> page = BegPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(BegPersonMapper::toDto));
    }

    @Override
    public Object queryAll(BegPersonQueryCriteria criteria){
        return BegPersonMapper.toDto(BegPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BegPersonDTO findById(String begId) {
        Optional<BegPerson> BegPerson = BegPersonRepository.findById(begId);
        ValidationUtil.isNull(BegPerson,"BegPerson","begId",begId);
        return BegPersonMapper.toDto(BegPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BegPersonDTO create(BegPerson resources) {
        resources.setBegId(IdUtil.simpleUUID()); 
        if(BegPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(BegPerson.class,"identity_num",resources.getIdentityNum());
        }
        return BegPersonMapper.toDto(BegPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BegPerson resources) {
        Optional<BegPerson> optionalBegPerson = BegPersonRepository.findById(resources.getBegId());
        ValidationUtil.isNull( optionalBegPerson,"BegPerson","id",resources.getBegId());
        BegPerson BegPerson = optionalBegPerson.get();
        BegPerson begPerson1 = BegPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(begPerson1 != null && !begPerson1.getBegId().equals(BegPerson.getBegId())){
            throw new EntityExistException(BegPerson.class,"identity_num",resources.getIdentityNum());
        }
        BegPerson.copy(resources);
        BegPersonRepository.save(BegPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String begId) {
        BegPersonRepository.deleteById(begId);
    }
}