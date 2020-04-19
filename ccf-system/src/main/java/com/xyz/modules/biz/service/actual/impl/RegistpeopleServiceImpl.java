package com.xyz.modules.biz.service.actual.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.actual.entity.Registpeople;
import com.xyz.modules.biz.service.actual.repo.RegistpeopleRepository;
import com.xyz.modules.biz.service.actual.RegistpeopleService;
import com.xyz.modules.biz.service.actual.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.RegistpeopleQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.RegistpeopleMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
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
* @date 2020-04-08
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RegistpeopleServiceImpl implements RegistpeopleService {

    @Autowired
    private RegistpeopleRepository RegistpeopleRepository;

    @Autowired
    private RegistpeopleMapper RegistpeopleMapper;

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
    public Object queryAll(RegistpeopleQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.info("**********户籍人员信息列表查询开始**********");
        Page<Registpeople> page = RegistpeopleRepository.findAll(audit.genSpecification(criteria),pageable);
        List<Registpeople> content = page.getContent();
        List<RegistpeopleDTO> registpeopleDTOS = RegistpeopleMapper.toDto(content);
        for (RegistpeopleDTO r:registpeopleDTOS){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), r.getPersonSex());
            r.setPersonSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), r.getNation());
            r.setNationStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), r.getNativeInfo());
            r.setNativeInfoStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), r.getMarriageFlag());
            r.setMarriageFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), r.getPartyFlag());
            r.setPartyFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), r.getEducationBg());
            r.setEducationBgStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), r.getFaithType());
            r.setFaithTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), r.getVocationCode());
            r.setVocationCodeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), r.getRegisteredPlace());
            r.setRegisteredPlaceStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.YHZGX.getDistName(), r.getHouseheadRela());
            r.setHouseheadRelaStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), r.getResidence());
            r.setResidenceStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), r.getServiceAddr());
            r.setServiceAddrStr(dd == null ? "无数据" : dd);
            dd = deptRepository.findNameByCode(r.getUnitCode());
            r.setUnitCodeStr(dd);
            r.setCreator(userRepository.findById(r.getCreator()).orElse(new User()).getUsername());
            r.setOperName(userRepository.findById(r.getOperName()).orElse(new User()).getUsername());
            r.setStatusStr(ConstEnum.transSync(r.getStatus()));
            r.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), r.getStatusCd()));
            r.setUnitCodeStr(deptRepository.findNameByCode(r.getUnitCode()));
        }
        Map map = new HashMap();
        map.put("content", registpeopleDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.info("**********户籍人员信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(RegistpeopleQueryCriteria criteria){
        log.info("**********条件查询 Registpeople 列表**********");
        return RegistpeopleMapper.toDto(RegistpeopleRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public RegistpeopleDTO findById(String regisId) {
        log.info("********** 查询 Registpeople 详情**********");
        Optional<Registpeople> Registpeople = RegistpeopleRepository.findById(regisId);
        ValidationUtil.isNull(Registpeople,"Registpeople","regisId",regisId);
        return RegistpeopleMapper.toDto(Registpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegistpeopleDTO create(Registpeople resources) {
        log.info("********** 新增 Registpeople  **********");
        resources.setRegisId(IdUtil.simpleUUID());
        if(RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            log.info("**********   Registpeople identity_num 重复 新增失败  **********");
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        return RegistpeopleMapper.toDto(RegistpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Registpeople resources) {
        log.info("********** 修改 Registpeople  **********");
        Optional<Registpeople> optionalRegistpeople = RegistpeopleRepository.findById(resources.getRegisId());
        ValidationUtil.isNull( optionalRegistpeople,"Registpeople","id",resources.getRegisId());
        Registpeople Registpeople = optionalRegistpeople.get();
        Registpeople Registpeople1 = null;
        Registpeople1 = RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Registpeople1 != null && !Registpeople1.getRegisId().equals(Registpeople.getRegisId())){
            log.info("**********   Registpeople identity_num 重复 修改失败  **********");
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        Registpeople.copy(resources);
        RegistpeopleRepository.save(Registpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String regisId) {
        log.info("********** 删除 Registpeople  **********");
        RegistpeopleRepository.deleteById(regisId);
    }
}