package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.DrugPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.DrugPersonRepository;
import com.xyz.modules.biz.service.DrugPersonService;
import com.xyz.modules.biz.service.dto.DrugPersonDTO;
import com.xyz.modules.biz.service.dto.DrugPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.DrugPersonMapper;
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
public class DrugPersonServiceImpl implements DrugPersonService {

    @Autowired
    private DrugPersonRepository DrugPersonRepository;

    @Autowired
    private DrugPersonMapper DrugPersonMapper;

    @Override
    public Object queryAll(DrugPersonQueryCriteria criteria, Pageable pageable){
        Page<DrugPerson> page = DrugPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(DrugPersonMapper::toDto));
    }

    @Override
    public Object queryAll(DrugPersonQueryCriteria criteria){
        return DrugPersonMapper.toDto(DrugPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DrugPersonDTO findById(String drugId) {
        Optional<DrugPerson> DrugPerson = DrugPersonRepository.findById(drugId);
        ValidationUtil.isNull(DrugPerson,"DrugPerson","drugId",drugId);
        return DrugPersonMapper.toDto(DrugPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DrugPersonDTO create(DrugPerson resources) {
        resources.setDrugId(IdUtil.simpleUUID()); 
        if(DrugPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(DrugPerson.class,"identity_num",resources.getIdentityNum());
        }
        return DrugPersonMapper.toDto(DrugPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DrugPerson resources) {
        Optional<DrugPerson> optionalDrugPerson = DrugPersonRepository.findById(resources.getDrugId());
        ValidationUtil.isNull( optionalDrugPerson,"DrugPerson","id",resources.getDrugId());
        DrugPerson DrugPerson = optionalDrugPerson.get();
        DrugPerson person= DrugPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(person != null && !person.getDrugId().equals(DrugPerson.getDrugId())){
            throw new EntityExistException(DrugPerson.class,"identity_num",resources.getIdentityNum());
        }
        DrugPerson.copy(resources);
        DrugPersonRepository.save(DrugPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String drugId) {
        DrugPersonRepository.deleteById(drugId);
    }
}