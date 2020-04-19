package com.xyz.modules.biz.service.teenager.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.teenager.entity.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.teenager.repo.BizTeenagerBaseinfoRepository;
import com.xyz.modules.biz.service.teenager.BizTeenagerBaseinfoService;
import com.xyz.modules.biz.service.teenager.dto.BizTeenagerBaseinfoDTO;
import com.xyz.modules.biz.service.teenager.dto.BizTeenagerBaseinfoQueryCriteria;
import com.xyz.modules.biz.service.teenager.mapper.BizTeenagerBaseinfoMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表重点青少年/重点青少年基本信息--开始");
        Page<BizTeenagerBaseinfo> page = bizTeenagerBaseinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizTeenagerBaseinfoDTO> bizTeenagerBaseinfoDTOList = bizTeenagerBaseinfoMapper.toDto(page.getContent());
        for (BizTeenagerBaseinfoDTO mid: bizTeenagerBaseinfoDTOList) {
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex())); // 性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation())); // 民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag())); //  婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag()));// 政治面貌
            mid.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEducationBg()));//  学历
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType())); // 宗教信仰
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setPeopleTypeStr(dictDetailService.transDict(DictEnum.RYLX.getDistName(), mid.getPeopleTypeStr()));// 人员类型
            mid.setHomeSituStr(dictDetailService.transDict(DictEnum.JTQK.getDistName(), mid.getHomeSituStr()));
            mid.setCreator(userRepository.findById(mid.getCreator()).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(mid.getOperName()).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
            mid.setIfIllegalStr(ConstEnum.getBoolean(mid.getIfIllegal()));
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