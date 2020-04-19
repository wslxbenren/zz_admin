package com.xyz.modules.biz.service.org.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.repo.BuildheadInfoRepository;
import com.xyz.modules.biz.service.org.BuildheadInfoService;
import com.xyz.modules.biz.service.org.dto.BuildheadInfoDTO;
import com.xyz.modules.biz.service.org.qo.BuildheadInfoQueryCriteria;
import com.xyz.modules.biz.service.org.mapper.BuildheadInfoMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuditSpecification auditSpecification;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public Object queryAll(BuildheadInfoQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.info("**********楼长信息列表查询开始**********");
        Page<BuildheadInfo> page = BuildheadInfoRepository.findAll(auditSpecification.genSpecification(criteria)
        ,pageable);
        List<BuildheadInfoDTO> buildheadInfoDTOS = buildheadInfoMapper.toDto(page.getContent());
        for (BuildheadInfoDTO b:buildheadInfoDTOS){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), b.getSex());
            b.setSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), b.getNational());
            b.setNationalStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), b.getPoliticalStatus());
            b.setPoliticalStatusStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), b.getEducationBg());
            b.setEducationBgStr(dd == null ? "无数据" : dd);
            dd = deptRepository.findNameByCode(b.getUnitCode());
            b.setUnitCodeStr(dd);
            b.setAddrStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), b.getAddr()));
            b.setCreator(userRepository.findById(b.getCreator()).orElse(new User()).getUsername());
            b.setModifier(userRepository.findById(b.getModifier()).orElse(new User()).getUsername());
            b.setStatusStr(DictEnum.transSync(b.getStatus()));
            b.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), b.getStatusCd()));
        }
        Map map = new HashMap();
        map.put("content", buildheadInfoDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.info("**********楼长信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(BuildheadInfoQueryCriteria criteria){
        log.info("********** 查询BuildheadInfo  列表**********");
        return  buildheadInfoMapper.toDto(BuildheadInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public BuildheadInfoDTO findById(String id) {
        log.info("********** 查询BuildheadInfo 详情**********");
        Optional<BuildheadInfo> BuildheadInfo = BuildheadInfoRepository.findById(id);
        ValidationUtil.isNull(BuildheadInfo,"BuildheadInfo","id",id);
        return buildheadInfoMapper.toDto(BuildheadInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuildheadInfoDTO create(BuildheadInfo resources) {
        log.info("********** 新增 BuildheadInfo **********");
        resources.setId(IdUtil.simpleUUID());
        return buildheadInfoMapper.toDto(BuildheadInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BuildheadInfo resources) {
        log.info("********** 修改 BuildheadInfo **********");
        Optional<BuildheadInfo> optionalBuildheadInfo = BuildheadInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBuildheadInfo,"BuildheadInfo","id",resources.getId());
        BuildheadInfo BuildheadInfo = optionalBuildheadInfo.get();
        BuildheadInfo.copy(resources);
        BuildheadInfoRepository.save(BuildheadInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        log.info("********** 删除 BuildheadInfo **********");
        BuildheadInfoRepository.deleteById(id);
    }


}