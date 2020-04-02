package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.domain.SuspectedPersonnelInfo;

import com.xyz.modules.system.repository.SuspectedPersonnelInfoRepository;
import com.xyz.modules.system.service.SuspectedPersonnelInfoService;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoDTO;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoQueryCriteria;
import com.xyz.modules.system.service.mapper.SuspectedPersonnelInfoMapper;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
* @author dadovicn
* @date 2020-02-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuspectedPersonnelInfoServiceImpl implements SuspectedPersonnelInfoService {

    @Autowired
    private SuspectedPersonnelInfoRepository suspectedPersonnelInfoRepository;

    @Autowired
    private SuspectedPersonnelInfoMapper suspectedPersonnelInfoMapper;

    @Override
    public Object queryAll(SuspectedPersonnelInfoQueryCriteria criteria, Pageable pageable){
        Page<SuspectedPersonnelInfo> page = suspectedPersonnelInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(suspectedPersonnelInfoMapper::toDto));
    }

    @Override
    public Object queryAll(SuspectedPersonnelInfoQueryCriteria criteria){
        return suspectedPersonnelInfoMapper.toDto(suspectedPersonnelInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public SuspectedPersonnelInfoDTO findById(String pId) {
        Optional<SuspectedPersonnelInfo> suspectedPersonnelInfo = suspectedPersonnelInfoRepository.findById(pId);
        ValidationUtil.isNull(suspectedPersonnelInfo,"SuspectedPersonnelInfo","pId",pId);
        return suspectedPersonnelInfoMapper.toDto(suspectedPersonnelInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectedPersonnelInfoDTO create(SuspectedPersonnelInfo resources) {
        resources.setPeId(IdUtil.simpleUUID());
        return suspectedPersonnelInfoMapper.toDto(suspectedPersonnelInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SuspectedPersonnelInfo resources) {
        Optional<SuspectedPersonnelInfo> optionalSuspectedPersonnelInfo = suspectedPersonnelInfoRepository.findById(resources.getPeId());
        ValidationUtil.isNull( optionalSuspectedPersonnelInfo,"SuspectedPersonnelInfo","id",resources.getPeId());
        SuspectedPersonnelInfo suspectedPersonnelInfo = optionalSuspectedPersonnelInfo.get();
        suspectedPersonnelInfo.copy(resources);
        suspectedPersonnelInfoRepository.save(suspectedPersonnelInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String peId) {
        suspectedPersonnelInfoRepository.deleteById(peId);
    }
}