package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BuildheadInfoRepository;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoDTO;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BuildheadInfoMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.utils.PageUtil;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

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
        Page<BuildheadInfo> page = BuildheadInfoRepository.findAll(AuditSpecification.genSpecification(criteria)
        ,pageable);
        return PageUtil.toPage(page.map(BuildheadInfoMapper::toDto));
    }

    @Override
    public Object queryAll(BuildheadInfoQueryCriteria criteria){
        return BuildheadInfoMapper.toDto(BuildheadInfoRepository.findAll(AuditSpecification.genSpecification(criteria)));
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
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        resources.setCreator(null);//等
        return BuildheadInfoMapper.toDto(BuildheadInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BuildheadInfo resources) {
        Optional<BuildheadInfo> optionalBuildheadInfo = BuildheadInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBuildheadInfo,"BuildheadInfo","id",resources.getId());
        BuildheadInfo BuildheadInfo = optionalBuildheadInfo.get();
        resources.setUpdateTime(new Timestamp(new Date().getTime()));
        resources.setModifier(null);//等
        BuildheadInfo.copy(resources);
        BuildheadInfoRepository.save(BuildheadInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        BuildheadInfoRepository.deleteById(id);
    }
}