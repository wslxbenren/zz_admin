package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.MajorcaseInfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.MajorcaseInfoRepository;
import com.xyz.modules.biz.service.MajorcaseInfoService;
import com.xyz.modules.biz.service.dto.MajorcaseInfoDTO;
import com.xyz.modules.biz.service.dto.MajorcaseInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.MajorcaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author xjh
* @date 2020-04-05
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MajorcaseInfoServiceImpl implements MajorcaseInfoService {

    @Autowired
    private MajorcaseInfoRepository MajorcaseInfoRepository;

    @Autowired
    private MajorcaseInfoMapper MajorcaseInfoMapper;

    @Override
    public Object queryAll(MajorcaseInfoQueryCriteria criteria, Pageable pageable){
        Page<MajorcaseInfo> page = MajorcaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(MajorcaseInfoMapper::toDto));
    }

    @Override
    public Object queryAll(MajorcaseInfoQueryCriteria criteria){
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public MajorcaseInfoDTO findById(String id) {
        Optional<MajorcaseInfo> MajorcaseInfo = MajorcaseInfoRepository.findById(id);
        ValidationUtil.isNull(MajorcaseInfo,"MajorcaseInfo","id",id);
        return MajorcaseInfoMapper.toDto(MajorcaseInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MajorcaseInfoDTO create(MajorcaseInfo resources) {
        resources.setId(IdUtil.simpleUUID());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MajorcaseInfo resources) {
        resources.setUpdateTime(new Timestamp(new Date().getTime()));
        Optional<MajorcaseInfo> optionalMajorcaseInfo = MajorcaseInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMajorcaseInfo,"MajorcaseInfo","id",resources.getId());
        MajorcaseInfo MajorcaseInfo = optionalMajorcaseInfo.get();
        MajorcaseInfo.copy(resources);
        MajorcaseInfoRepository.save(MajorcaseInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        MajorcaseInfoRepository.deleteById(id);
    }
}