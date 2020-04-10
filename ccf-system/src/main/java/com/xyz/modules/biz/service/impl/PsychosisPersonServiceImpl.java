package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.PsychosisPerson;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.PsychosisPersonRepository;
import com.xyz.modules.biz.service.PsychosisPersonService;
import com.xyz.modules.biz.service.dto.PsychosisPersonDTO;
import com.xyz.modules.biz.service.dto.PsychosisPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.PsychosisPersonMapper;
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
public class PsychosisPersonServiceImpl implements PsychosisPersonService {

    @Autowired
    private PsychosisPersonRepository PsychosisPersonRepository;

    @Autowired
    private PsychosisPersonMapper PsychosisPersonMapper;

    @Override
    public Object queryAll(PsychosisPersonQueryCriteria criteria, Pageable pageable){
        Page<PsychosisPerson> page = PsychosisPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(PsychosisPersonMapper::toDto));
    }

    @Override
    public Object queryAll(PsychosisPersonQueryCriteria criteria){
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public PsychosisPersonDTO findById(String psychosisId) {
        Optional<PsychosisPerson> PsychosisPerson = PsychosisPersonRepository.findById(psychosisId);
        ValidationUtil.isNull(PsychosisPerson,"PsychosisPerson","psychosisId",psychosisId);
        return PsychosisPersonMapper.toDto(PsychosisPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PsychosisPersonDTO create(PsychosisPerson resources) {
        resources.setPsychosisId(IdUtil.simpleUUID()); 
        if(PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(PsychosisPerson.class,"identity_num",resources.getIdentityNum());
        }
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PsychosisPerson resources) {
        Optional<PsychosisPerson> optionalPsychosisPerson = PsychosisPersonRepository.findById(resources.getPsychosisId());
        ValidationUtil.isNull( optionalPsychosisPerson,"PsychosisPerson","id",resources.getPsychosisId());
        PsychosisPerson PsychosisPerson = optionalPsychosisPerson.get();
        PsychosisPerson psychosisPerson = PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(psychosisPerson != null && !psychosisPerson.getPsychosisId().equals(PsychosisPerson.getPsychosisId())){
            throw new EntityExistException(PsychosisPerson.class,"identity_num",resources.getIdentityNum());
        }
        PsychosisPerson.copy(resources);
        PsychosisPersonRepository.save(PsychosisPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String psychosisId) {
        PsychosisPersonRepository.deleteById(psychosisId);
    }
}