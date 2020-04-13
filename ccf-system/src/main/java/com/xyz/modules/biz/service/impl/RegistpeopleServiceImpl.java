package com.xyz.modules.biz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.modules.biz.repository.RegistpeopleRepository;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.dto.RegistpeopleQueryCriteria;
import com.xyz.modules.biz.service.mapper.RegistpeopleMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Override
    @Transactional
    public Object queryAll(RegistpeopleQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.debug("**********户籍人员信息列表查询开始**********");
        Page<Registpeople> page = RegistpeopleRepository.findAll(audit.genSpecification(criteria),pageable);
        List<Registpeople> content = page.getContent();
        List<RegistpeopleDTO> registpeopleDTOS = RegistpeopleMapper.toDto(content);
        for (RegistpeopleDTO r:registpeopleDTOS){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), r.getPersonSex());
            r.setPersonSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), r.getNation());
            r.setNationStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), r.getNativeInfo());
            r.setNativeInfoStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), r.getMarriageFlag());
            r.setMarriageFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), r.getPartyFlag());
            r.setPersonSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), r.getEducationBg());
            r.setEducationBgStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), r.getFaithType());
            r.setFaithTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), r.getVocationCode());
            r.setVocationCodeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), r.getRegisteredPlace());
            r.setRegisteredPlaceStr(dd == null ? "无数据" : dd);

            dd = deptRepository.findNameByCode(r.getUnitCode());
            r.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", registpeopleDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.debug("**********户籍人员信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(RegistpeopleQueryCriteria criteria){
        return RegistpeopleMapper.toDto(RegistpeopleRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public RegistpeopleDTO findById(String regisId) {
        Optional<Registpeople> Registpeople = RegistpeopleRepository.findById(regisId);
        ValidationUtil.isNull(Registpeople,"Registpeople","regisId",regisId);
        return RegistpeopleMapper.toDto(Registpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegistpeopleDTO create(Registpeople resources) {
        resources.setRegisId(IdUtil.simpleUUID());
        if(RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        return RegistpeopleMapper.toDto(RegistpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Registpeople resources) {
        Optional<Registpeople> optionalRegistpeople = RegistpeopleRepository.findById(resources.getRegisId());
        ValidationUtil.isNull( optionalRegistpeople,"Registpeople","id",resources.getRegisId());
        Registpeople Registpeople = optionalRegistpeople.get();
        Registpeople Registpeople1 = null;
        Registpeople1 = RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Registpeople1 != null && !Registpeople1.getRegisId().equals(Registpeople.getRegisId())){
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        Registpeople.copy(resources);
        RegistpeopleRepository.save(Registpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String regisId) {
        RegistpeopleRepository.deleteById(regisId);
    }
}