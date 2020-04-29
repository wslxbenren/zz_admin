package com.xyz.modules.biz.service.route.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.route.entity.Convenientinfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.route.repo.ConvenientinfoRepository;
import com.xyz.modules.biz.service.route.ConvenientinfoService;
import com.xyz.modules.biz.service.route.dto.ConvenientinfoDTO;
import com.xyz.modules.biz.service.route.qo.ConvenientinfoQueryCriteria;
import com.xyz.modules.biz.service.route.mapper.ConvenientinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
            f.setRouteTypeStr(dictDetailService.transDict(DictEnum.LXLX.getDictId(), f.getRouteType()));// 路线类型
            f.setSubordunitAddrcode(dictDetailService.transDict(DictEnum.ADDRESS.getDictId(),f.getSubordunitAddrcodeStr()));//隶属单位省市县编码
            f.setJurisdunitAddrcodeStr(dictDetailService.transDict(DictEnum.ADDRESS.getDictId(),f.getJurisdunitAddrcodeStr()));//管辖单位省市县编码
            f.setSecuhiddenLevelStr(dictDetailService.transDict(DictEnum.ZAYHDJ.getDictId(), f.getSecuhiddenLevel()));//治安隐患等级
            f.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(),f.getStatus()));//数据状态
            f.setUnitCodeStr(deptRepository.findNameByCode(f.getUnitCode()));
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
//        resources.setCreator(SecurityUtils.getUsername());
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
//        resources.setOperName(SecurityUtils.getUsername());
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