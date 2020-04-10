package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Leftbehind;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.LeftbehindRepository;
import com.xyz.modules.biz.service.LeftbehindService;
import com.xyz.modules.biz.service.dto.LeftbehindDTO;
import com.xyz.modules.biz.service.dto.LeftbehindQueryCriteria;
import com.xyz.modules.biz.service.mapper.LeftbehindMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


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
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Object queryAll(LeftbehindQueryCriteria criteria, Pageable pageable){
        log.info("查询列表实有人口/留守人员信息 --开始");
        Page<Leftbehind> page = LeftbehindRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<LeftbehindDTO> leftbehindList = LeftbehindMapper.toDto(page.getContent());
        for (LeftbehindDTO mid: leftbehindList) {
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.ZJDM.getDistName(), mid.getIdentityNum());
            mid.setIdentityNumStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), mid.getPersonSex());
            mid.setPersonSexStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), mid.getNation());
            mid.setNationStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getNativeInfo());
            mid.setNativeInfoStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.HYZK.getDistName(), mid.getMarriageFlag());
            mid.setMarriageFlagStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZZMM.getDistName(), mid.getPartyFlag());
            mid.setPartyFlagStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), mid.getEducationBg());
            mid.setEducationBgStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), mid.getFaithType());
            mid.setFaithTypeStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), mid.getVocationCode());
            mid.setVocationCodeStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getRegisteredPlace());
            mid.setRegisteredPlaceStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getResidence());
            mid.setResidenceStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.YHZGX.getDistName(), mid.getMainmemRela());
            mid.setMainmemRelaStr(dd == null ? "无数据":dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", leftbehindList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(LeftbehindQueryCriteria criteria){
        return LeftbehindMapper.toDto(LeftbehindRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public LeftbehindDTO findById(String leftId) {
            log.info("查询详情实有人口/留守人员信息 --开始");
            if (StringUtils.isBlank(leftId)){
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
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        if(LeftbehindRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Leftbehind.class,"identity_num",resources.getIdentityNum());
        }
        return LeftbehindMapper.toDto(LeftbehindRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Leftbehind resources) {
            log.info("修改实有人口/留守人员信息 --开始");
            if (StringUtils.isBlank(resources.getLeftId())){
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
            JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
            resources.setOperName(u.getId());
            Leftbehind.copy(resources);
            LeftbehindRepository.save(Leftbehind);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String leftId) {
            log.info("删除实有人口/留守人员信息 --开始");
            if (StringUtils.isBlank(leftId)){
                throw new BadRequestException("主键ID不能为空");
            }
            LeftbehindRepository.deleteById(leftId);

    }
}