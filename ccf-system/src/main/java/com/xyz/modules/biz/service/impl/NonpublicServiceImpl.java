package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Nonpublic;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.NonpublicRepository;
import com.xyz.modules.biz.service.NonpublicService;
import com.xyz.modules.biz.service.dto.NonpublicDTO;
import com.xyz.modules.biz.service.dto.NonpublicQueryCriteria;
import com.xyz.modules.biz.service.mapper.NonpublicMapper;
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
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NonpublicServiceImpl implements NonpublicService {

    @Autowired
    private NonpublicRepository NonpublicRepository;

    @Autowired
    private NonpublicMapper NonpublicMapper;

    @Override
    public Object queryAll(NonpublicQueryCriteria criteria, Pageable pageable){
        Page<Nonpublic> page = NonpublicRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(NonpublicMapper::toDto));
    }

    @Override
    public Object queryAll(NonpublicQueryCriteria criteria){
        return NonpublicMapper.toDto(NonpublicRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public NonpublicDTO findById(String nonId) {
        Optional<Nonpublic> Nonpublic = NonpublicRepository.findById(nonId);
        ValidationUtil.isNull(Nonpublic,"Nonpublic","nonId",nonId);
        return NonpublicMapper.toDto(Nonpublic.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NonpublicDTO create(Nonpublic resources) {
        resources.setNonId(IdUtil.simpleUUID()); 
        return NonpublicMapper.toDto(NonpublicRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Nonpublic resources) {
        Optional<Nonpublic> optionalNonpublic = NonpublicRepository.findById(resources.getNonId());
        ValidationUtil.isNull( optionalNonpublic,"Nonpublic","id",resources.getNonId());
        Nonpublic Nonpublic = optionalNonpublic.get();
        Nonpublic.copy(resources);
        NonpublicRepository.save(Nonpublic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String nonId) {
        NonpublicRepository.deleteById(nonId);
    }
}