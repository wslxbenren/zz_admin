package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.RegistpeopleRepository;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.dto.RegistpeopleQueryCriteria;
import com.xyz.modules.biz.service.mapper.RegistpeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author lx
* @date 2020-04-08
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RegistpeopleServiceImpl implements RegistpeopleService {

    @Autowired
    private RegistpeopleRepository RegistpeopleRepository;

    @Autowired
    private RegistpeopleMapper RegistpeopleMapper;

    @Override
    public Object queryAll(RegistpeopleQueryCriteria criteria, Pageable pageable){
        Page<Registpeople> page = RegistpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(RegistpeopleMapper::toDto));
    }

    @Override
    public Object queryAll(RegistpeopleQueryCriteria criteria){
        return RegistpeopleMapper.toDto(RegistpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public RegistpeopleDTO findById(String regisId) {
        Optional<Registpeople> Registpeople = RegistpeopleRepository.findById(regisId);
        ValidationUtil.isNull(Registpeople,"Registpeople","regisId",regisId);
        return RegistpeopleMapper.toDto(Registpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegistpeopleDTO create(Registpeople resources) {
        resources.setRegisId(IdUtil.simpleUUID());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        resources.setOperDate(new Timestamp(new Date().getTime()));
        resources.setCreator(null);//等
        if(RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        return RegistpeopleMapper.toDto(RegistpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Registpeople resources) {
        Optional<Registpeople> optionalRegistpeople = RegistpeopleRepository.findById(resources.getRegisId());
        ValidationUtil.isNull( optionalRegistpeople,"Registpeople","id",resources.getRegisId());
        Registpeople Registpeople = optionalRegistpeople.get();
        Registpeople Registpeople1 = null;
        Registpeople1 = RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Registpeople1 != null && !Registpeople1.getRegisId().equals(Registpeople.getRegisId())){
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }

        Registpeople.copy(resources);
        RegistpeopleRepository.save(Registpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String regisId) {
        RegistpeopleRepository.deleteById(regisId);
    }
}