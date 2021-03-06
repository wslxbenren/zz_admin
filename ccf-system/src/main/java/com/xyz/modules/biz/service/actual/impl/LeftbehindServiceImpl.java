package com.xyz.modules.biz.service.actual.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.audit.mongo.service.ModifyRecordsRepo;
import com.xyz.modules.biz.service.actual.entity.Leftbehind;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.CompareFieldsService;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.actual.repo.LeftbehindRepository;
import com.xyz.modules.biz.service.actual.LeftbehindService;
import com.xyz.modules.biz.service.actual.dto.LeftbehindDTO;
import com.xyz.modules.biz.service.actual.qo.LeftbehindQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.LeftbehindMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author xjh
 * @date 2020-04-08
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LeftbehindServiceImpl implements LeftbehindService {

    @Autowired
    private LeftbehindRepository LeftbehindRepository;

    @Autowired
    private LeftbehindMapper LeftbehindMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModifyRecordsRepo recordsRepo;

    @Autowired
    private CompareFieldsService compareFieldsService;


    @Override
    @Transactional
    public Object queryAll(LeftbehindQueryCriteria criteria, Pageable pageable) {
        log.info("查询列表实有人口/留守人员信息 --开始");
        if (StringUtils.isNotBlank(criteria.getResidence())) {
            String addrPrefix = ConstEnum.genAddrPrefix(criteria.getResidence());
            if (addrPrefix.length() != 6) {
                criteria.setResidenceWithDownGrade(dictDetailService.addrWithDownGrade(addrPrefix, DictEnum.ADDRESS.getDictId()));
            } else {
                criteria.setResidenceWithDownGrade(new ArrayList<String>() {{
                    add(addrPrefix);
                }});
            }
        }
        Page<Leftbehind> page = LeftbehindRepository.findAll(audit.genSpecification(criteria), pageable);
        List<LeftbehindDTO> leftbehindList = LeftbehindMapper.toDto(page.getContent());
        for (LeftbehindDTO mid : leftbehindList) {
            String dd = dictDetailService.transDict(DictEnum.ZJDM.getDictId(), mid.getIdentityNum());
            mid.setIdentityNumStr(dictDetailService.transDict(DictEnum.ZJDM.getDictId(), mid.getIdentityNum())); // 公民身份号码
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(), mid.getPersonSex())); //性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(), mid.getNation())); //民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo())); // 籍贯
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDictId(), mid.getMarriageFlag())); // 婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDictId(), mid.getPartyFlag())); //  政治面貌
            mid.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(), mid.getEducationBg())); //  学历
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDictId(), mid.getFaithType())); //  宗教信仰
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode())); // 职业类别
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace())); // 户籍地
            mid.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getResidence())); //  现住地
            mid.setMainmemRelaStr(dictDetailService.transDict(DictEnum.YHZGX.getDictId(), mid.getMainmemRela())); //  与留守人员关系
            mid.setHealthyStr(dictDetailService.transDict(DictEnum.JKZK.getDictId(), mid.getHealthy())); //  健康状况
            mid.setLeftbehindTypeStr(dictDetailService.transDict(DictEnum.LSRYLX.getDictId(), mid.getLeftbehindType())); //  留守人员类型
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), mid.getStatusCd()));
            mid.setMainmemHealthStr(dictDetailService.transDict(DictEnum.JKZK.getDictId(), mid.getMainmemHealth()));
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setHouseholdIdStr(ConstEnum.getBoolean(mid.getHouseholdId().substring(1)));
            mid.setServicePlaceCodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
            mid.setMainmemAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getMainmemAddrcode()));
        }
        Map map = new HashMap();
        map.put("content", leftbehindList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages", page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(LeftbehindQueryCriteria criteria) {
        return LeftbehindMapper.toDto(LeftbehindRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public LeftbehindDTO findById(String leftId) {
        log.info("查询详情实有人口/留守人员信息 --开始");
        if (StringUtils.isBlank(leftId)) {
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Leftbehind> Leftbehind = LeftbehindRepository.findById(leftId);
        ValidationUtil.isNull(Leftbehind, "Leftbehind", "leftId", leftId);
        return LeftbehindMapper.toDto(Leftbehind.get());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeftbehindDTO create(Leftbehind resources) {
        log.info("新增实有人口/留守人员信息 --开始");
        resources.setLeftId(IdUtil.simpleUUID());
        if (LeftbehindRepository.findByIdentityNum(resources.getIdentityNum()) != null) {
            throw new EntityExistException(Leftbehind.class, "identity_num", resources.getIdentityNum());
        }
        return LeftbehindMapper.toDto(LeftbehindRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Leftbehind resources) {
        log.info("修改实有人口/留守人员信息 --开始");
        if (StringUtils.isBlank(resources.getLeftId())) {
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Leftbehind> optionalLeftbehind = LeftbehindRepository.findById(resources.getLeftId());
        ValidationUtil.isNull(optionalLeftbehind, "Leftbehind", "id", resources.getLeftId());
        Leftbehind Leftbehind = optionalLeftbehind.get();
        Leftbehind Leftbehind1 = null;
        Leftbehind1 = LeftbehindRepository.findByIdentityNum(resources.getIdentityNum());
        if (Leftbehind1 != null && !Leftbehind1.getLeftId().equals(Leftbehind.getLeftId())) {
            throw new EntityExistException(Leftbehind.class, "identity_num", resources.getIdentityNum());
        }

        ModifyRecords modifyRecords = new ModifyRecords();
        modifyRecords.setModifyContent(compareFieldsService.
                compareModifyRecords(Leftbehind, resources, new String[]{"leftId", "effDate", "expDate","operDate","operName","creator", "createTime"}));
        modifyRecords.setEntityId(Leftbehind.getLeftId());
        modifyRecords.setId(IdUtil.simpleUUID());
        modifyRecords.setOperName(SecurityUtils.getUsername());
        modifyRecords.setOperTime(LocalDateTime.now());
        modifyRecords.setDeptName(deptRepository.findNameByCode(Leftbehind.getUnitCode()));
        modifyRecords.setCreateTime(Leftbehind.getCreateTime().toLocalDateTime());
        modifyRecords.setCreator(Leftbehind.getCreator());
        recordsRepo.save(modifyRecords);

        Leftbehind.copy(resources);
        LeftbehindRepository.save(Leftbehind);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String leftId) {
        log.info("删除实有人口/留守人员信息 --开始");
        if (StringUtils.isBlank(leftId)) {
            throw new BadRequestException("主键ID不能为空");
        }
        LeftbehindRepository.deleteById(leftId);

    }

    @Override
    public Boolean validateIdentityNum(String id, String identityNum) {
        Long isNull = LeftbehindRepository.validateIdentityNum(identityNum);
        if (isNull == 0) {
            return false;
        } else if (isNull == 1) {
            isNull = LeftbehindRepository.validateIdentityNumById(id, identityNum);
            return isNull == 1 ? false : true;
        } else {
            return true;
        }
    }

    @Override
    public List<ModifyRecords> findModifyRecordsById(String leftId) {
        List<ModifyRecords> records = recordsRepo.findAllByEntityId(leftId);
        return records;
    }
}