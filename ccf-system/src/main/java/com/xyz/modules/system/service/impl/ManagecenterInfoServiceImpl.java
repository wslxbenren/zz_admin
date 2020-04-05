package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.domain.ManagecenterInfo;
import ValidationUtil;
import com.xyz.modules.system.repository.ManagecenterInfoRepository;
import com.xyz.modules.system.service.ManagecenterInfoService;
import com.xyz.modules.system.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.system.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.system.service.mapper.ManagecenterInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import PageUtil;
import QueryHelp;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManagecenterInfoServiceImpl implements ManagecenterInfoService {

    @Autowired
    private ManagecenterInfoRepository ManagecenterInfoRepository;

    @Autowired
    private ManagecenterInfoMapper ManagecenterInfoMapper;

    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ManagecenterInfoMapper::toDto));
    }

    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria){
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ManagecenterInfoDTO findById(String id) {
        Optional<ManagecenterInfo> ManagecenterInfo = ManagecenterInfoRepository.findById(id);
        ValidationUtil.isNull(ManagecenterInfo,"ManagecenterInfo","id",id);
        return ManagecenterInfoMapper.toDto(ManagecenterInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManagecenterInfoDTO create(ManagecenterInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManagecenterInfo resources) {
        Optional<ManagecenterInfo> optionalManagecenterInfo = ManagecenterInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManagecenterInfo,"ManagecenterInfo","id",resources.getId());
        ManagecenterInfo ManagecenterInfo = optionalManagecenterInfo.get();
        ManagecenterInfo.copy(resources);
        ManagecenterInfoRepository.save(ManagecenterInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ManagecenterInfoRepository.deleteById(id);
    }
}