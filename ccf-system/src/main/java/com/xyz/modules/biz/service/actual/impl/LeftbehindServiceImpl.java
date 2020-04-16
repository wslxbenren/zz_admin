package com.xyz.modules.biz.service.actual.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.actual.entity.Leftbehind;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
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

    @Override
    @Transactional
    public Object queryAll(LeftbehindQueryCriteria criteria, Pageable pageable){
        log.info("查询列表实有人口/留守人员信息 --开始");
        Page<Leftbehind> page = LeftbehindRepository.findAll(audit.genSpecification(criteria),pageable);
        List<LeftbehindDTO> leftbehindList = LeftbehindMapper.toDto(page.getContent());
        for (LeftbehindDTO mid: leftbehindList) {
            String dd = dictDetailService.transDict(DictEnum.ZJDM.getDistName(), mid.getIdentityNum());
            mid.setIdentityNumStr(dd == null ? "无数据": dd); // 公民身份号码
            dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex());
            mid.setPersonSexStr(dd == null ? "无数据": dd); //性别
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation());
            mid.setNationStr(dd == null ? "无数据": dd); //民族
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getNativeInfo());
            mid.setNativeInfoStr(dd == null ? "无数据": dd); //籍贯
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag());
            mid.setMarriageFlagStr(dd == null ? "无数据": dd); // 婚姻状况
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag());
            mid.setPartyFlagStr(dd == null ? "无数据": dd); //  政治面貌
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEducationBg());
            mid.setEducationBgStr(dd == null ? "无数据": dd); //  学历
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType());
            mid.setFaithTypeStr(dd == null ? "无数据": dd); //  宗教信仰
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), mid.getVocationCode());
            mid.setVocationCodeStr(dd == null ? "无数据": dd); //  职业类别
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getRegisteredPlace());
            mid.setRegisteredPlaceStr(dd == null ? "无数据": dd); //  户籍地
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getResidence());
            mid.setResidenceStr(dd == null ? "无数据": dd); //  现住地
            dd = dictDetailService.transDict(DictEnum.YHZGX.getDistName(), mid.getMainmemRela());
            mid.setMainmemRelaStr(dd == null ? "无数据": dd); //  与留守人员关系

            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", leftbehindList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(LeftbehindQueryCriteria criteria){
        return LeftbehindMapper.toDto(LeftbehindRepository.findAll(audit.genSpecification(criteria)));
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