package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ForeignersRepository;
import com.xyz.modules.biz.service.ForeignersService;
import com.xyz.modules.biz.service.dto.ForeignersDTO;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
import com.xyz.modules.biz.service.mapper.ForeignersMapper;
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
* @author dadovicn
* @date 2020-04-08
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ForeignersServiceImpl implements ForeignersService {

    @Autowired
    private ForeignersRepository ForeignersRepository;

    @Autowired
    private ForeignersMapper ForeignersMapper;

    @Override
    public Object queryAll(ForeignersQueryCriteria criteria, Pageable pageable){
        Page<Foreigners> page = ForeignersRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ForeignersMapper::toDto));
    }

    @Override
    public Object queryAll(ForeignersQueryCriteria criteria){
        return ForeignersMapper.toDto(ForeignersRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ForeignersDTO findById(String foreId) {
        Optional<Foreigners> Foreigners = ForeignersRepository.findById(foreId);
        ValidationUtil.isNull(Foreigners,"Foreigners","foreId",foreId);
        return ForeignersMapper.toDto(Foreigners.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ForeignersDTO create(Foreigners resources) {
        resources.setForeId(IdUtil.simpleUUID()); 
        return ForeignersMapper.toDto(ForeignersRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Foreigners resources) {
        Optional<Foreigners> optionalForeigners = ForeignersRepository.findById(resources.getForeId());
        ValidationUtil.isNull( optionalForeigners,"Foreigners","id",resources.getForeId());
        Foreigners Foreigners = optionalForeigners.get();
        Foreigners.copy(resources);
        ForeignersRepository.save(Foreigners);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String foreId) {
        ForeignersRepository.deleteById(foreId);
    }
}