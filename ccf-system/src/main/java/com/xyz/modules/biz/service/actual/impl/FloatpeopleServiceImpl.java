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
        log.debug("**********流动人口信息列**********");
        if (StringUtils.isNotBlank(criteria.getResidence())) {
            String addrPrefix = ConstEnum.genAddrPrefix(criteria.getResidence());
            if(addrPrefix.length() != 6) {
                criteria.setResidenceWithDownGrade(dictDetailService.addrWithDownGrade(addrPrefix, DictEnum.ADDRESS.getDictId()));
            } else {
                criteria.setResidenceWithDownGrade(new ArrayList<String>() {{ add(addrPrefix); }});
            }
        }
        Page<Floatpeople> page = FloatpeopleRepository.findAll(audit.genSpecification(criteria),pageable);
        List<FloatpeopleDTO> floatpeopleDTOS = FloatpeopleMapper.toDto(page.getContent());
        for (FloatpeopleDTO mid:floatpeopleDTOS){
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(), mid.getPersonSex()));
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(), mid.getNation()));
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDictId(), mid.getMarriageFlag()));
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDictId(), mid.getPartyFlag()));
            mid.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(), mid.getEducationBg()));
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDictId(), mid.getFaithType()));
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setServicePlaceCodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
            mid.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getResidence()));
            mid.setIntoCauseStr(dictDetailService.transDict(DictEnum.LRYY.getDictId(), mid.getIntoCause()));
            mid.setCardTypeStr(dictDetailService.transDict(DictEnum.BZLX.getDictId(), mid.getCardType()));
            mid.setResidTypeStr(dictDetailService.transDict(DictEnum.ZSLX.getDictId(), mid.getResidType()));
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), mid.getStatusCd()));
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setIfImportStr(ConstEnum.getBoolean(mid.getIfImport()));

        }
        Map map = new HashMap();
        map.put("content", floatpeopleDTOS);
        map.put("totalElements", page.getTotalElements());
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

    @Override
    public Boolean validateIdentityNum(String id,String identityNum) {
        Long isNull = FloatpeopleRepository.validateIdentityNum(identityNum);
        if (isNull == 0) {
            return false;
        } else if (isNull == 1) {
            isNull = FloatpeopleRepository.validateIdentityNumById(id, identityNum);
            return isNull == 1 ? false : true;
        }else {
            return true;
        }
    }
}