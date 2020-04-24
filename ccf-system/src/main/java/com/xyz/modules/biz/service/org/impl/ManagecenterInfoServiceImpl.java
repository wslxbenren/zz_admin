package com.xyz.modules.biz.service.org.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.org.ManagecenterInfoService;
import com.xyz.modules.biz.service.org.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.org.entity.ManagecenterInfo;
import com.xyz.modules.biz.service.org.mapper.ManagecenterInfoMapper;
import com.xyz.modules.biz.service.org.qo.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.org.repo.ManagecenterInfoRepository;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
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

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        log.debug("**********综治中心信息列表查询开始**********");
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll(auditSpecification.genSpecification(criteria),pageable);
        List<ManagecenterInfoDTO> midList = ManagecenterInfoMapper.toDto(page.getContent());
        for (ManagecenterInfoDTO mid:midList ) {
            String dd = dictDetailService.transDict(DictEnum.JGCJ.getDictId(), mid.getGrage());
            mid.setGrageStr(dd == null ? "无数据":dd);
            mid.setAddrStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getAddr()));
            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setModifier(userRepository.findById(Optional.ofNullable(mid.getModifier()).orElse("")).orElse(new User()).getUsername());
            mid.setUnitCodeStr(dd);
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), mid.getStatusCd()));
        }
        Map map = new HashMap();
        map.put("content", midList);
        map.put("totalElements", page.getTotalElements());

        return map;
    }

    @Override
    @Transactional
    public Object queryAll(ManagecenterInfoQueryCriteria criteria){
        log.debug("**********条件查询ManagecenterInfo 列表**********");
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public ManagecenterInfoDTO findById(String id) {
        log.debug("********** 查询ManagecenterInfo 详情**********");
        Optional<ManagecenterInfo> ManagecenterInfo = ManagecenterInfoRepository.findById(id);
        ValidationUtil.isNull(ManagecenterInfo,"ManagecenterInfo","id",id);
        return ManagecenterInfoMapper.toDto(ManagecenterInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManagecenterInfoDTO create(ManagecenterInfo resources) {
        log.debug("********** 新增ManagecenterInfo  **********");
        resources.setId(IdUtil.simpleUUID());
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManagecenterInfo resources) {
        log.debug("********** 修改ManagecenterInfo  **********");
        Optional<ManagecenterInfo> optionalManagecenterInfo = ManagecenterInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManagecenterInfo,"ManagecenterInfo","id",resources.getId());
        ManagecenterInfo ManagecenterInfo = optionalManagecenterInfo.get();
        ManagecenterInfo.copy(resources);
        ManagecenterInfoRepository.save(ManagecenterInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        log.debug("********** 删除ManagecenterInfo  **********");
        ManagecenterInfoRepository.deleteById(id);
    }
}