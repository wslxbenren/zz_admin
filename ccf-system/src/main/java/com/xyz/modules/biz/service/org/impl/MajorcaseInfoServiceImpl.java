package com.xyz.modules.biz.service.org.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.org.repo.MajorcaseInfoRepository;
import com.xyz.modules.biz.service.org.MajorcaseInfoService;
import com.xyz.modules.biz.service.org.dto.MajorcaseInfoDTO;
import com.xyz.modules.biz.service.org.qo.MajorcaseInfoQueryCriteria;
import com.xyz.modules.biz.service.org.mapper.MajorcaseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MajorcaseInfoServiceImpl implements MajorcaseInfoService {

    @Autowired
    private MajorcaseInfoRepository MajorcaseInfoRepository;

    @Autowired
    private MajorcaseInfoMapper MajorcaseInfoMapper;

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
    public Object queryAll(MajorcaseInfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表综治组织/重大案件事件--开始");
        if (StringUtils.isNotBlank(criteria.getOccurAddr())) {
            String addrPrefix = ConstEnum.genAddrPrefix(criteria.getOccurAddr());
            if(addrPrefix.length() != 6) {
                criteria.setOccurAddrWithDownGrade(dictDetailService.addrWithDownGrade(addrPrefix, DictEnum.ADDRESS.getDictId()));
            } else {
                criteria.setOccurAddrWithDownGrade(new ArrayList<String>() {{ add(addrPrefix); }});
            }
        }
        Page<MajorcaseInfo> page = MajorcaseInfoRepository.findAll(auditSpecification.genSpecification(criteria),pageable);
        List<MajorcaseInfoDTO> majorcaseInfoList = MajorcaseInfoMapper.toDto(page.getContent());
        for (MajorcaseInfoDTO mid: majorcaseInfoList) {
            mid.setOccurAddrStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getOccurAddr()));
            String dd = dictDetailService. transDict(DictEnum.AJFJ.getDistName(), mid.getCaseGrage());
            mid.setCaseGrageStr(dd == null ? "无数据": dd); // 案（事）件分级
            dd = dictDetailService. transDict(DictEnum.AJLX.getDistName(), mid.getCaseType());
            mid.setCaseTypeStr(dd == null ? "无数据": dd); //  案（事）件类型
            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setModifier(userRepository.findById(Optional.ofNullable(mid.getModifier()).orElse("")).orElse(new User()).getUsername());
            mid.setUnitCodeStr(dd);
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
        }
        Map map = new HashMap();
        map.put("content", majorcaseInfoList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(MajorcaseInfoQueryCriteria criteria){
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.findAll(auditSpecification.genSpecification(criteria)));
    }

    @Override
    public MajorcaseInfoDTO findById(String id) {
            log.info("查询详情综治组织/重大案件事件--开始");
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
        log.info("新增综治组织/重大案件事件--开始");
        resources.setId(IdUtil.simpleUUID());
        return MajorcaseInfoMapper.toDto(MajorcaseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MajorcaseInfo resources) {
            log.info("修改综治组织/重大案件事件--开始");
            if (StringUtils.isBlank(resources.getId())){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<MajorcaseInfo> optionalMajorcaseInfo = MajorcaseInfoRepository.findById(resources.getId());
            ValidationUtil.isNull( optionalMajorcaseInfo,"MajorcaseInfo","id",resources.getId());
            MajorcaseInfo MajorcaseInfo = optionalMajorcaseInfo.get();
            MajorcaseInfo.copy(resources);
            MajorcaseInfoRepository.save(MajorcaseInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        log.info("删除综治组织/重大案件事件--开始");
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        MajorcaseInfoRepository.deleteById(id);
    }
}