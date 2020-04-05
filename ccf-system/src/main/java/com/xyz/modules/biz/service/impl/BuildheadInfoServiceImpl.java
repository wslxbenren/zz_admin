package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.BuildheadInfo;
import ValidationUtil;
import com.xyz.modules.biz.repository.BuildheadInfoRepository;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoDTO;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BuildheadInfoMapper;
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
public class BuildheadInfoServiceImpl implements BuildheadInfoService {

    @Autowired
    private BuildheadInfoRepository BuildheadInfoRepository;

    @Autowired
    private BuildheadInfoMapper BuildheadInfoMapper;

    @Override
    public Object queryAll(BuildheadInfoQueryCriteria criteria, Pageable pageable){
        Page<BuildheadInfo> page = BuildheadInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(BuildheadInfoMapper::toDto));
    }

    @Override
    public Object queryAll(BuildheadInfoQueryCriteria criteria){
        return BuildheadInfoMapper.toDto(BuildheadInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BuildheadInfoDTO findById(String id) {
        Optional<BuildheadInfo> BuildheadInfo = BuildheadInfoRepository.findById(id);
        ValidationUtil.isNull(BuildheadInfo,"BuildheadInfo","id",id);
        return BuildheadInfoMapper.toDto(BuildheadInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuildheadInfoDTO create(BuildheadInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return BuildheadInfoMapper.toDto(BuildheadInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BuildheadInfo resources) {
        Optional<BuildheadInfo> optionalBuildheadInfo = BuildheadInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBuildheadInfo,"BuildheadInfo","id",resources.getId());
        BuildheadInfo BuildheadInfo = optionalBuildheadInfo.get();
        BuildheadInfo.copy(resources);
        BuildheadInfoRepository.save(BuildheadInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        BuildheadInfoRepository.deleteById(id);
    }
}