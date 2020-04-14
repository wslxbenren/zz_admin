package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Convenientinfo;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsDTO;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ConvenientinfoRepository;
import com.xyz.modules.biz.service.ConvenientinfoService;
import com.xyz.modules.biz.service.dto.ConvenientinfoDTO;
import com.xyz.modules.biz.service.dto.ConvenientinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ConvenientinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：护路护线/护路护线基本信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ConvenientinfoServiceImpl implements ConvenientinfoService {

    @Autowired
    private ConvenientinfoRepository ConvenientinfoRepository;

    @Autowired
    private ConvenientinfoMapper ConvenientinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(ConvenientinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表护路护线/护路护线基本信息--开始");
        Page<Convenientinfo> page = ConvenientinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<ConvenientinfoDTO> convenientinfoDTOList = ConvenientinfoMapper.toDto(page.getContent());
        for (ConvenientinfoDTO f:convenientinfoDTOList){
            String dd = dictDetailService.transDict(DictEnum.LXLX.getDistName(), f.getRouteType());
            f.setRouteTypeStr(dd == null ? "无数据" : dd);// 路线类型
            dd = dictDetailService.transDict(DictEnum.ZAYHDJ.getDistName(), f.getSecuhiddenLevel());
            f.setSecuhiddenLevelStr(dd == null ? "无数据" : dd);//治安隐患等级

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", convenientinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(ConvenientinfoQueryCriteria criteria){
        return ConvenientinfoMapper.toDto(ConvenientinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public ConvenientinfoDTO findById(String conId) {
        log.info("详情护路护线/护路护线基本信息--开始");
        if (StringUtils.isBlank(conId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Convenientinfo> Convenientinfo = ConvenientinfoRepository.findById(conId);
        ValidationUtil.isNull(Convenientinfo,"Convenientinfo","conId",conId);
        return ConvenientinfoMapper.toDto(Convenientinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConvenientinfoDTO create(Convenientinfo resources) {
        log.info("新增护路护线/护路护线基本信息--开始");
        resources.setConId(IdUtil.simpleUUID()); 
        return ConvenientinfoMapper.toDto(ConvenientinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Convenientinfo resources) {
        log.info("修改护路护线/护路护线基本信息--开始");
        if (StringUtils.isBlank(resources.getConId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Convenientinfo> optionalConvenientinfo = ConvenientinfoRepository.findById(resources.getConId());
        ValidationUtil.isNull( optionalConvenientinfo,"Convenientinfo","id",resources.getConId());
        Convenientinfo Convenientinfo = optionalConvenientinfo.get();
        Convenientinfo.copy(resources);
        ConvenientinfoRepository.save(Convenientinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String conId) {
        log.info("删除护路护线/护路护线基本信息--开始");
        if (StringUtils.isBlank(conId)){
            throw new BadRequestException("主键ID不能为空");
        }
        ConvenientinfoRepository.deleteById(conId);
    }
}