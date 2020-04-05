package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import ValidationUtil;
import com.xyz.modules.biz.repository.ManageleadresponsInfoRepository;
import com.xyz.modules.biz.service.ManageleadresponsInfoService;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoDTO;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManageleadresponsInfoMapper;
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
public class ManageleadresponsInfoServiceImpl implements ManageleadresponsInfoService {

    @Autowired
    private ManageleadresponsInfoRepository ManageleadresponsInfoRepository;

    @Autowired
    private ManageleadresponsInfoMapper ManageleadresponsInfoMapper;

    @Override
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria, Pageable pageable){
        Page<ManageleadresponsInfo> page = ManageleadresponsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ManageleadresponsInfoMapper::toDto));
    }

    @Override
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria){
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ManageleadresponsInfoDTO findById(String id) {
        Optional<ManageleadresponsInfo> ManageleadresponsInfo = ManageleadresponsInfoRepository.findById(id);
        ValidationUtil.isNull(ManageleadresponsInfo,"ManageleadresponsInfo","id",id);
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManageleadresponsInfoDTO create(ManageleadresponsInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManageleadresponsInfo resources) {
        Optional<ManageleadresponsInfo> optionalManageleadresponsInfo = ManageleadresponsInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManageleadresponsInfo,"ManageleadresponsInfo","id",resources.getId());
        ManageleadresponsInfo ManageleadresponsInfo = optionalManageleadresponsInfo.get();
        ManageleadresponsInfo.copy(resources);
        ManageleadresponsInfoRepository.save(ManageleadresponsInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ManageleadresponsInfoRepository.deleteById(id);
    }
}