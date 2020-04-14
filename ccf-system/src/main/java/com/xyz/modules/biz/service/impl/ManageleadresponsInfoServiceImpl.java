package com.xyz.modules.biz.service.impl;


import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.ManageleadresponsInfoRepository;
import com.xyz.modules.biz.service.ManageleadresponsInfoService;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoDTO;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManageleadresponsInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @author xjh
* @date 2020-04-05
 *  功能模块 ： 综治组织/领导责任制
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManageleadresponsInfoServiceImpl implements ManageleadresponsInfoService {

    @Autowired
    private ManageleadresponsInfoRepository ManageleadresponsInfoRepository;

    @Autowired
    private ManageleadresponsInfoMapper ManageleadresponsInfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification auditSpecification;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表综治组织/领导责任制 -- 开始");
        Page<ManageleadresponsInfo> page = ManageleadresponsInfoRepository.findAll(auditSpecification.genSpecification(criteria),pageable);
        List<ManageleadresponsInfoDTO> manageleadresponsInfoList = ManageleadresponsInfoMapper.toDto(page.getContent());
        for (ManageleadresponsInfoDTO mid: manageleadresponsInfoList) {
            String dd = dictDetailService. transDict(DictEnum.JGCJ.getDistName(), mid.getAreaGrage());
            mid.setAreaGrageStr(dd == null ? "无数据": dd); // 被实施地区层级
            dd = dictDetailService. transDict(DictEnum.JGCJ.getDistName(), mid.getImplementerGrage());
            mid.setImplementerGrageStr(dd == null ? "无数据": dd); //  实施主体层级
            dd = dictDetailService. transDict(DictEnum.ZCZL.getDistName(), mid.getPolicyType());
            mid.setPolicyTypeStr(dd == null ? "无数据": dd);  //政策种类

            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", manageleadresponsInfoList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;

    }

    @Override
    @Transactional
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria){
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public ManageleadresponsInfoDTO findById(String id) {
        log.info("查询详情综治组织/领导责任制 -- 开始");
            if (StringUtils.isBlank(id)){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<ManageleadresponsInfo> ManageleadresponsInfo = ManageleadresponsInfoRepository.findById(id);
            ValidationUtil.isNull(ManageleadresponsInfo,"ManageleadresponsInfo","id",id);
            return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfo.get());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManageleadresponsInfoDTO create(ManageleadresponsInfo resources) {
        log.info("新增综治组织/领导责任制 -- 开始");
        resources.setId(IdUtil.simpleUUID());
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.save(resources));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManageleadresponsInfo resources) {
            log.info("修改综治组织/领导责任制 -- 开始");
            if (StringUtils.isBlank(resources.getId())){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<ManageleadresponsInfo> optionalManageleadresponsInfo = ManageleadresponsInfoRepository.findById(resources.getId());
            ValidationUtil.isNull( optionalManageleadresponsInfo,"ManageleadresponsInfo","id",resources.getId());
            ManageleadresponsInfo ManageleadresponsInfo = optionalManageleadresponsInfo.get();
            ManageleadresponsInfo.copy(resources);
            ManageleadresponsInfoRepository.save(ManageleadresponsInfo);



    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        log.info("删除综治组织/领导责任制 -- 开始");
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        ManageleadresponsInfoRepository.deleteById(id);


    }
}