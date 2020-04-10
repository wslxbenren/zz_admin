package com.xyz.modules.biz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.repository.BuildheadInfoRepository;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoDTO;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BuildheadInfoMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author lx
* @date 2020-04-06
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BuildheadInfoServiceImpl implements BuildheadInfoService {

    @Autowired
    private BuildheadInfoRepository BuildheadInfoRepository;

    @Autowired
    private BuildheadInfoMapper buildheadInfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuditSpecification auditSpecification;

    @Override
    @Transactional(readOnly = false)
    public Object queryAll(BuildheadInfoQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.debug("**********楼长信息列表查询开始**********");
        Page<BuildheadInfo> page = BuildheadInfoRepository.findAll(auditSpecification.genSpecification(criteria)
        ,pageable);
        List<BuildheadInfoDTO> buildheadInfoDTOS = buildheadInfoMapper.toDto(page.getContent());
        for (BuildheadInfoDTO b:buildheadInfoDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), b.getSex());
            b.setSexStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), b.getNational());
            b.setNational(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), b.getPoliticalStatus());
            b.setPoliticalStatusStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), b.getEducationBgStr());
            b.setEducationBgStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", buildheadInfoDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.debug("**********楼长信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    public Object queryAll(BuildheadInfoQueryCriteria criteria){
        return  buildheadInfoMapper.toDto(BuildheadInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public BuildheadInfoDTO findById(String id) {
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BuildheadInfo> BuildheadInfo = BuildheadInfoRepository.findById(id);
        ValidationUtil.isNull(BuildheadInfo,"BuildheadInfo","id",id);
        return buildheadInfoMapper.toDto(BuildheadInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuildheadInfoDTO create(BuildheadInfo resources) {
        resources.setId(IdUtil.simpleUUID());
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return buildheadInfoMapper.toDto(BuildheadInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BuildheadInfo resources) {
        Optional<BuildheadInfo> optionalBuildheadInfo = BuildheadInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBuildheadInfo,"BuildheadInfo","id",resources.getId());
        BuildheadInfo BuildheadInfo = optionalBuildheadInfo.get();
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setModifier(u.getId());
        BuildheadInfo.copy(resources);
        BuildheadInfoRepository.save(BuildheadInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        BuildheadInfoRepository.deleteById(id);
    }


}