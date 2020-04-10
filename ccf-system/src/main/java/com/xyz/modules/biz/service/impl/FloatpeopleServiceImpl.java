package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Floatpeople;
import com.xyz.modules.biz.repository.FloatpeopleRepository;
import com.xyz.modules.biz.service.FloatpeopleService;
import com.xyz.modules.biz.service.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.dto.FloatpeopleQueryCriteria;
import com.xyz.modules.biz.service.mapper.FloatpeopleMapper;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author lx
* @date 2020-04-09
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FloatpeopleServiceImpl implements FloatpeopleService {

    @Autowired
    private FloatpeopleRepository FloatpeopleRepository;

    @Autowired
    private FloatpeopleMapper FloatpeopleMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Object queryAll(FloatpeopleQueryCriteria criteria, Pageable pageable){
        Page<Floatpeople> page = FloatpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<FloatpeopleDTO> floatpeopleDTOS = FloatpeopleMapper.toDto(page.getContent());
        for (FloatpeopleDTO f:floatpeopleDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), f.getEducationBg());
            f.setEducationBgStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", floatpeopleDTOS);
        map.put("totalElements", page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(FloatpeopleQueryCriteria criteria){
        return FloatpeopleMapper.toDto(FloatpeopleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public FloatpeopleDTO findById(String floatId) {
        if (StringUtils.isBlank(floatId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Floatpeople> Floatpeople = FloatpeopleRepository.findById(floatId);
        ValidationUtil.isNull(Floatpeople,"Floatpeople","floatId",floatId);
        return FloatpeopleMapper.toDto(Floatpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FloatpeopleDTO create(Floatpeople resources) {
        resources.setFloatId(IdUtil.simpleUUID());
        if(FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return FloatpeopleMapper.toDto(FloatpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Floatpeople resources) {
        if (StringUtils.isBlank(resources.getFloatId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Floatpeople> optionalFloatpeople = FloatpeopleRepository.findById(resources.getFloatId());
        ValidationUtil.isNull( optionalFloatpeople,"Floatpeople","id",resources.getFloatId());
        Floatpeople Floatpeople = optionalFloatpeople.get();
        Floatpeople Floatpeople1 = null;
        Floatpeople1 = FloatpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Floatpeople1 != null && !Floatpeople1.getFloatId().equals(Floatpeople.getFloatId())){
            throw new EntityExistException(Floatpeople.class,"identity_num",resources.getIdentityNum());
        }
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        Floatpeople.copy(resources);
        FloatpeopleRepository.save(Floatpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String floatId) {
        FloatpeopleRepository.deleteById(floatId);
    }
}