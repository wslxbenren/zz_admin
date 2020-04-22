package com.xyz.modules.biz.service.actual.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import com.xyz.modules.biz.service.actual.repo.FloatpeopleRepository;
import com.xyz.modules.biz.service.actual.FloatpeopleService;
import com.xyz.modules.biz.service.actual.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.FloatpeopleQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.FloatpeopleMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
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
* @date 2020-04-09
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FloatpeopleServiceImpl implements FloatpeopleService {

    @Autowired
    private FloatpeopleRepository FloatpeopleRepository;

    @Autowired
    private FloatpeopleMapper FloatpeopleMapper;

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
    public Object queryAll(FloatpeopleQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.debug("**********流动人口信息列表查询开始**********");
        Page<Floatpeople> page = FloatpeopleRepository.findAll(audit.genSpecification(criteria),pageable);
        List<FloatpeopleDTO> floatpeopleDTOS = FloatpeopleMapper.toDto(page.getContent());
        for (FloatpeopleDTO f:floatpeopleDTOS){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), f.getEducationBg());
            f.setEducationBgStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getServicePlaceCode());
            f.setServicePlaceCodeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), f.getResidence());
            f.setResidenceStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.LRYY.getDistName(), f.getIntoCause());
            f.setIntoCauseStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.BZLX.getDistName(), f.getCardType());
            f.setCardTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZSLX.getDistName(), f.getResidType());
            f.setResidTypeStr(dd == null ? "无数据" : dd);

            f.setCreator(userRepository.findById(f.getCreator()).orElse(new User()).getUsername());
            f.setOperName(userRepository.findById(f.getOperName()).orElse(new User()).getUsername());
            f.setStatusStr(ConstEnum.transSync(f.getStatus()));
            f.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), f.getStatusCd()));
            f.setUnitCodeStr(deptRepository.findNameByCode(f.getUnitCode()));
            f.setIfImportStr(ConstEnum.getBoolean(f.getIfImport()));

        }
        Map map = new HashMap();
        map.put("content", floatpeopleDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.debug("**********流动人口信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(FloatpeopleQueryCriteria criteria){
        log.debug("**********条件查询 Floatpeople 列表**********");
        return FloatpeopleMapper.toDto(FloatpeopleRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public FloatpeopleDTO findById(String floatId) {
        log.debug("********** 查询 Floatpeople 详情**********");
        Optional<Floatpeople> Floatpeople = FloatpeopleRepository.findById(floatId);
        ValidationUtil.isNull(Floatpeople,"Floatpeople","floatId",floatId);
        return FloatpeopleMapper.toDto(Floatpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FloatpeopleDTO create(Floatpeople resources) {
        log.debug("********** 新增 Floatpeople  **********");
        resources.setFloatId(IdUtil.simpleUUID());
        if(FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            log.debug("**********   Floatpeople identity_num 重复 新增失败 **********");
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        return FloatpeopleMapper.toDto(FloatpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Floatpeople resources) {
        log.debug("********** 修改 Floatpeople  **********");
        Optional<Floatpeople> optionalFloatpeople = FloatpeopleRepository.findById(resources.getFloatId());
        ValidationUtil.isNull( optionalFloatpeople,"Floatpeople","id",resources.getFloatId());
        Floatpeople Floatpeople = optionalFloatpeople.get();
        Floatpeople Floatpeople1 = null;
        Floatpeople1 = FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Floatpeople1 != null && !Floatpeople1.getFloatId().equals(Floatpeople.getFloatId())){
            log.debug("**********   Floatpeople identity_num 重复 修改失败 **********");
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        Floatpeople.copy(resources);
        FloatpeopleRepository.save(Floatpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String floatId) {
        log.debug("********** 删除 Floatpeople  **********");
        FloatpeopleRepository.deleteById(floatId);
    }
}