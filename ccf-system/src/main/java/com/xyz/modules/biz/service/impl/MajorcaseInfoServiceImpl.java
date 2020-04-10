package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.MajorcaseInfo;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.MajorcaseInfoRepository;
import com.xyz.modules.biz.service.MajorcaseInfoService;
import com.xyz.modules.biz.service.dto.MajorcaseInfoDTO;
import com.xyz.modules.biz.service.dto.MajorcaseInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.MajorcaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Object queryAll(MajorcaseInfoQueryCriteria criteria, Pageable pageable){
        Page<MajorcaseInfo> page = MajorcaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<MajorcaseInfoDTO> majorcaseInfoList = MajorcaseInfoMapper.toDto(page.getContent());
        for (MajorcaseInfoDTO mid: majorcaseInfoList) {
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getOccurAddr());
            mid.setOccurAddrStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.AJFJ.getDistName(), mid.getCaseGrage());
            mid.setCaseGrageStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.AJLX.getDistName(), mid.getCaseType());
            mid.setCaseTypeStr(dd == null ? "无数据":dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", majorcaseInfoList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(MajorcaseInfoQueryCriteria criteria){
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public MajorcaseInfoDTO findById(String id) {
            if (StringUtils.isBlank(id)){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<MajorcaseInfo> MajorcaseInfo = MajorcaseInfoRepository.findById(id);
            ValidationUtil.isNull(MajorcaseInfo, "MajorcaseInfo", "id", id);
            return MajorcaseInfoMapper.toDto(MajorcaseInfo.get());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MajorcaseInfoDTO create(MajorcaseInfo resources) {
        resources.setId(IdUtil.simpleUUID());
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MajorcaseInfo resources) {
            if (StringUtils.isBlank(resources.getId())){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<MajorcaseInfo> optionalMajorcaseInfo = MajorcaseInfoRepository.findById(resources.getId());
            ValidationUtil.isNull( optionalMajorcaseInfo,"MajorcaseInfo","id",resources.getId());
            MajorcaseInfo MajorcaseInfo = optionalMajorcaseInfo.get();
            JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
            resources.setModifier(u.getId());
            MajorcaseInfo.copy(resources);
            MajorcaseInfoRepository.save(MajorcaseInfo);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        MajorcaseInfoRepository.deleteById(id);


    }
}