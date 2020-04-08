package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Floatpeople;
import com.xyz.modules.biz.repository.FloatpeopleRepository;
import com.xyz.modules.biz.service.FloatpeopleService;
import com.xyz.modules.biz.service.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.dto.FloatpeopleQueryCriteria;
import com.xyz.modules.biz.service.mapper.FloatpeopleMapper;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FloatpeopleServiceImpl implements FloatpeopleService {

    @Autowired
    private FloatpeopleRepository FloatpeopleRepository;

    @Autowired
    private FloatpeopleMapper FloatpeopleMapper;

    @Override
    public Object queryAll(FloatpeopleQueryCriteria criteria, Pageable pageable){
        Page<Floatpeople> page = FloatpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(FloatpeopleMapper::toDto));
    }

    @Override
    public Object queryAll(FloatpeopleQueryCriteria criteria){
        return FloatpeopleMapper.toDto(FloatpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public FloatpeopleDTO findById(String floatId) {
        Optional<Floatpeople> Floatpeople = FloatpeopleRepository.findById(floatId);
        ValidationUtil.isNull(Floatpeople,"Floatpeople","floatId",floatId);
        return FloatpeopleMapper.toDto(Floatpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FloatpeopleDTO create(Floatpeople resources) {
        resources.setFloatId(IdUtil.simpleUUID()); 
        if(FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        return FloatpeopleMapper.toDto(FloatpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Floatpeople resources) {
        Optional<Floatpeople> optionalFloatpeople = FloatpeopleRepository.findById(resources.getFloatId());
        ValidationUtil.isNull( optionalFloatpeople,"Floatpeople","id",resources.getFloatId());
        Floatpeople Floatpeople = optionalFloatpeople.get();
        Floatpeople Floatpeople1 = null;
        Floatpeople1 = FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Floatpeople1 != null && !Floatpeople1.getFloatId().equals(Floatpeople.getFloatId())){
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        Floatpeople.copy(resources);
        FloatpeopleRepository.save(Floatpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String floatId) {
        FloatpeopleRepository.deleteById(floatId);
    }
}