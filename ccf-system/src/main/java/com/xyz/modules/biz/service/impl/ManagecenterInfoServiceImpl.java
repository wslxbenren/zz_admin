package com.xyz.modules.biz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.biz.repository.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.ManagecenterInfoService;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManagecenterInfoMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author lx
* @date 2020-04-07
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManagecenterInfoServiceImpl implements ManagecenterInfoService {

    @Autowired
    private ManagecenterInfoRepository ManagecenterInfoRepository;

    @Autowired
    private ManagecenterInfoMapper ManagecenterInfoMapper;

    @Autowired
    private DictDetailRepository dictDetailRepository;

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private DictDetailService dictDetailService;


    @Autowired
    private AuditSpecification auditSpecification;

    @Autowired
    private DeptRepository deptRepository;


    @Override
    @Transactional
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.debug("**********综治中心信息列表查询开始**********");
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll(auditSpecification.genSpecification(criteria),pageable);
        List<ManagecenterInfoDTO> midList = ManagecenterInfoMapper.toDto(page.getContent());
        for (ManagecenterInfoDTO mid:midList ) {
            String dd = dictDetailService.transDict(DictEnum.JGCJ.getDistName(), mid.getGrage());
            mid.setGrageStr(dd == null ? "无数据":dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getAddr());
            mid.setAddr(dd == null ? "无数据":dd);

            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", midList);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.debug("**********综治中心信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(ManagecenterInfoQueryCriteria criteria){
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public ManagecenterInfoDTO findById(String id) {
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
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
        if (StringUtils.isBlank(resources.getId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<ManagecenterInfo> optionalManagecenterInfo = ManagecenterInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManagecenterInfo,"ManagecenterInfo","id",resources.getId());
        ManagecenterInfo ManagecenterInfo = optionalManagecenterInfo.get();
        ManagecenterInfo.copy(resources);
        ManagecenterInfoRepository.save(ManagecenterInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        ManagecenterInfoRepository.deleteById(id);
    }
}