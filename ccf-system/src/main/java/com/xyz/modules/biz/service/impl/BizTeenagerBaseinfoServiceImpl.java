package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.dto.LeftbehindDTO;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.BizTeenagerBaseinfoRepository;
import com.xyz.modules.biz.service.BizTeenagerBaseinfoService;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoDTO;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizTeenagerBaseinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：重点青少年/重点青少年基本信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizTeenagerBaseinfoServiceImpl implements BizTeenagerBaseinfoService {

    @Autowired
    private BizTeenagerBaseinfoRepository bizTeenagerBaseinfoRepository;

    @Resource
    private BizTeenagerBaseinfoMapper bizTeenagerBaseinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表重点青少年/重点青少年基本信息--开始");
        Page<BizTeenagerBaseinfo> page = bizTeenagerBaseinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizTeenagerBaseinfoDTO> bizTeenagerBaseinfoDTOList = bizTeenagerBaseinfoMapper.toDto(page.getContent());
        for (BizTeenagerBaseinfoDTO mid: bizTeenagerBaseinfoDTOList) {
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex());
            mid.setPersonSexStr(dd == null ? "无数据" : dd); // 性别
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation());
            mid.setNationStr(dd == null ? "无数据" : dd); // 民族
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag());
            mid.setMarriageFlagStr(dd == null ? "无数据":dd); //  婚姻状况
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag());
            mid.setPartyFlagStr(dd == null ? "无数据":dd);// 政治面貌
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEducationBg());
            mid.setEducationBgStr(dd == null ? "无数据":dd);//  学历
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType());
            mid.setFaithTypeStr(dd == null ? "无数据":dd); // 宗教信仰
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), mid.getVocationCode());
            mid.setVocationCodeStr(dd == null ? "无数据":dd); // 职业类别
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getRegisteredPlace());
            mid.setRegisteredPlaceStr(dd == null ? "无数据":dd);// 户籍地
            dd = dictDetailService.transDict(DictEnum.RYLX.getDistName(), mid.getPeopleTypeStr());
            mid.setPeopleTypeStr(dd == null ? "无数据":dd);// 人员类型
            dd = dictDetailService.transDict(DictEnum.JTQK.getDistName(), mid.getHomeSituStr());
            mid.setHomeSituStr(dd == null ? "无数据":dd); //  家庭情况

            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", bizTeenagerBaseinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria){
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public BizTeenagerBaseinfoDTO findById(String teenId) {
        log.info("详情重点青少年/重点青少年基本信息--开始");
        if (StringUtils.isBlank(teenId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizTeenagerBaseinfo> bizTeenagerBaseinfo = bizTeenagerBaseinfoRepository.findById(teenId);
        ValidationUtil.isNull(bizTeenagerBaseinfo,"BizTeenagerBaseinfo","teenId",teenId);
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizTeenagerBaseinfoDTO create(BizTeenagerBaseinfo resources) {
        log.info("新增重点青少年/重点青少年基本信息--开始");
        resources.setTeenId(IdUtil.simpleUUID()); 
        if(bizTeenagerBaseinfoRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(BizTeenagerBaseinfo.class,"identity_num",resources.getIdentityNum());
        }
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizTeenagerBaseinfo resources) {
        log.info("修改重点青少年/重点青少年基本信息--开始");
        if (StringUtils.isBlank(resources.getTeenId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizTeenagerBaseinfo> optionalBizTeenagerBaseinfo = bizTeenagerBaseinfoRepository.findById(resources.getTeenId());
        ValidationUtil.isNull( optionalBizTeenagerBaseinfo,"BizTeenagerBaseinfo","id",resources.getTeenId());
        BizTeenagerBaseinfo bizTeenagerBaseinfo = optionalBizTeenagerBaseinfo.get();
        BizTeenagerBaseinfo bizTeenagerBaseinfo1 = null;
        bizTeenagerBaseinfo1 = bizTeenagerBaseinfoRepository.findByIdentityNum(resources.getIdentityNum());
        if(bizTeenagerBaseinfo1 != null && !bizTeenagerBaseinfo1.getTeenId().equals(bizTeenagerBaseinfo.getTeenId())){
            throw new EntityExistException(BizTeenagerBaseinfo.class,"identity_num",resources.getIdentityNum());
        }
        bizTeenagerBaseinfo.copy(resources);
        bizTeenagerBaseinfoRepository.save(bizTeenagerBaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String teenId) {
        log.info("删除重点青少年/重点青少年基本信息--开始");
        if (StringUtils.isBlank(teenId)){
            throw new BadRequestException("主键ID不能为空");
        }
        bizTeenagerBaseinfoRepository.deleteById(teenId);
    }
}