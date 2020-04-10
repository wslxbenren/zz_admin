package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Convenientinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ConvenientinfoRepository;
import com.xyz.modules.biz.service.ConvenientinfoService;
import com.xyz.modules.biz.service.dto.ConvenientinfoDTO;
import com.xyz.modules.biz.service.dto.ConvenientinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ConvenientinfoMapper;
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
public class ConvenientinfoServiceImpl implements ConvenientinfoService {

    @Autowired
    private ConvenientinfoRepository ConvenientinfoRepository;

    @Autowired
    private ConvenientinfoMapper ConvenientinfoMapper;

    @Override
    public Object queryAll(ConvenientinfoQueryCriteria criteria, Pageable pageable){
        Page<Convenientinfo> page = ConvenientinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ConvenientinfoMapper::toDto));
    }

    @Override
    public Object queryAll(ConvenientinfoQueryCriteria criteria){
        return ConvenientinfoMapper.toDto(ConvenientinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ConvenientinfoDTO findById(String conId) {
        Optional<Convenientinfo> Convenientinfo = ConvenientinfoRepository.findById(conId);
        ValidationUtil.isNull(Convenientinfo,"Convenientinfo","conId",conId);
        return ConvenientinfoMapper.toDto(Convenientinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConvenientinfoDTO create(Convenientinfo resources) {
        resources.setConId(IdUtil.simpleUUID()); 
        return ConvenientinfoMapper.toDto(ConvenientinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Convenientinfo resources) {
        Optional<Convenientinfo> optionalConvenientinfo = ConvenientinfoRepository.findById(resources.getConId());
        ValidationUtil.isNull( optionalConvenientinfo,"Convenientinfo","id",resources.getConId());
        Convenientinfo Convenientinfo = optionalConvenientinfo.get();
        Convenientinfo.copy(resources);
        ConvenientinfoRepository.save(Convenientinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String conId) {
        ConvenientinfoRepository.deleteById(conId);
    }
}