package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.BizSecurKeyareas;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizSecurKeyareasRepository;
import com.xyz.modules.biz.service.BizSecurKeyareasService;
import com.xyz.modules.biz.service.dto.BizSecurKeyareasDTO;
import com.xyz.modules.biz.service.dto.BizSecurKeyareasQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSecurKeyareasMapper;
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
public class BizSecurKeyareasServiceImpl implements BizSecurKeyareasService {

    @Autowired
    private BizSecurKeyareasRepository bizSecurKeyareasRepository;

    @Autowired
    private BizSecurKeyareasMapper bizSecurKeyareasMapper;

    @Override
    public Object queryAll(BizSecurKeyareasQueryCriteria criteria, Pageable pageable){
        Page<BizSecurKeyareas> page = bizSecurKeyareasRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSecurKeyareasMapper::toDto));
    }

    @Override
    public Object queryAll(BizSecurKeyareasQueryCriteria criteria){
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareasRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSecurKeyareasDTO findById(String keyId) {
        Optional<BizSecurKeyareas> bizSecurKeyareas = bizSecurKeyareasRepository.findById(keyId);
        ValidationUtil.isNull(bizSecurKeyareas,"BizSecurKeyareas","keyId",keyId);
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareas.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurKeyareasDTO create(BizSecurKeyareas resources) {
        resources.setKeyId(IdUtil.simpleUUID()); 
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareasRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurKeyareas resources) {
        Optional<BizSecurKeyareas> optionalBizSecurKeyareas = bizSecurKeyareasRepository.findById(resources.getKeyId());
        ValidationUtil.isNull( optionalBizSecurKeyareas,"BizSecurKeyareas","id",resources.getKeyId());
        BizSecurKeyareas bizSecurKeyareas = optionalBizSecurKeyareas.get();
        bizSecurKeyareas.copy(resources);
        bizSecurKeyareasRepository.save(bizSecurKeyareas);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String keyId) {
        bizSecurKeyareasRepository.deleteById(keyId);
    }
}