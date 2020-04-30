package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.secur.entity.Suspectinfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.secur.repo.SuspectinfoRepository;
import com.xyz.modules.biz.service.secur.SuspectinfoService;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoDTO;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.SuspectinfoMapper;
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
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：社会治安管理/命案犯罪嫌疑人信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuspectinfoServiceImpl implements SuspectinfoService {

    @Autowired
    private SuspectinfoRepository SuspectinfoRepository;

    @Autowired
    private SuspectinfoMapper SuspectinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/命案犯罪嫌疑人信息--开始");

        if (criteria.getResidence()!= null & criteria.getResidence() != "") {
            String addrPrefix = ConstEnum.genAddrPrefix(criteria.getResidence());
            if(addrPrefix.length() != 6) {
                criteria.setResidenceWithDownGrade(dictDetailService.addrWithDownGrade(addrPrefix, DictEnum.ADDRESS.getDictId()));
            } else {
                criteria.setResidenceWithDownGrade(new ArrayList<String>() {{ add(addrPrefix); }});
            }
        }
        Page<Suspectinfo> page = SuspectinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<SuspectinfoDTO> suspectinfoDTOList = SuspectinfoMapper.toDto(page.getContent());
        for (SuspectinfoDTO f:suspectinfoDTOList){
            f.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(), f.getPersonSex()));// 性别
            f.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(), f.getNation()));//民族
            f.setCardTypeStr(dictDetailService.transDict(DictEnum.ZJDM.getDictId(), f.getCardType()));// 证件代码
            f.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getNativeInfo()));//籍贯
            f.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDictId(), f.getMarriageFlag()));//婚姻状况
            f.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDictId(), f.getPartyFlag()));// 政治面貌
            f.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDictId(), f.getFaithType()));// 宗教信仰
            f.setVocationCodeStr(dictDetailService.transDict(DictEnum.ZYLB.getDictId(), f.getVocationCode())); // 职业类别
            f.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getRegisteredPlace()));// 户籍地
            f.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getResidence()));// 现住地
            f.setCountryStr(dictDetailService.transDict(DictEnum.GJ_DQ.getDictId(), f.getCountry()));// 国籍
            f.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(), f.getEducationBg()));// 学历
            f.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), f.getStatusCd()));// 数据状态
            f.setUnitCodeStr(deptRepository.findNameByCode(f.getUnitCode()));
            f.setServiceAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getServiceAddrcode()));
            f.setCreator(userRepository.findById(Optional.ofNullable(f.getCreator()).orElse("")).orElse(new User()).getUsername());
            f.setOperName(userRepository.findById(Optional.ofNullable(f.getOperName()).orElse("")).orElse(new User()).getUsername());
            f.setIfMinorsStr(ConstEnum.getBoolean(f.getIfMinors()));
            f.setIfPsychopathStr(ConstEnum.getBoolean(f.getIfPsychopath()));
            f.setIfTeenagersStr(ConstEnum.getBoolean(f.getIfTeenagers()));
        }
        Map map = new HashMap();
        map.put("content", suspectinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria){
        return SuspectinfoMapper.toDto(SuspectinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public SuspectinfoDTO findById(String suspId) {
        log.info("详情社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> Suspectinfo = SuspectinfoRepository.findById(suspId);
        ValidationUtil.isNull(Suspectinfo,"Suspectinfo","suspId",suspId);
        return SuspectinfoMapper.toDto(Suspectinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectinfoDTO create(Suspectinfo resources) {
        log.info("新增社会治安管理/命案犯罪嫌疑人信息--开始");
        resources.setSuspId(IdUtil.simpleUUID());
        resources.setCreator(SecurityUtils.getUserId());
        return SuspectinfoMapper.toDto(SuspectinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Suspectinfo resources) {
        log.info("修改社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(resources.getSuspId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> optionalSuspectinfo = SuspectinfoRepository.findById(resources.getSuspId());
        ValidationUtil.isNull( optionalSuspectinfo,"Suspectinfo","id",resources.getSuspId());
        Suspectinfo Suspectinfo = optionalSuspectinfo.get();
        Suspectinfo.copy(resources);
        SuspectinfoRepository.save(Suspectinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String suspId) {
        log.info("删除社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        SuspectinfoRepository.deleteById(suspId);
    }
}