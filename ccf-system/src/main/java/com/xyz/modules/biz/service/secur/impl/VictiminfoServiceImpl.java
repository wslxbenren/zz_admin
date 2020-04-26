package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.secur.entity.Victiminfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.secur.repo.VictiminfoRepository;
import com.xyz.modules.biz.service.secur.VictiminfoService;
import com.xyz.modules.biz.service.secur.dto.VictiminfoDTO;
import com.xyz.modules.biz.service.secur.dto.VictiminfoQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.VictiminfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：社会治安管理/命案受害人信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VictiminfoServiceImpl implements VictiminfoService {

    @Autowired
    private VictiminfoRepository VictiminfoRepository;

    @Autowired
    private VictiminfoMapper VictiminfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(VictiminfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/命案受害人信息--开始");
        Page<Victiminfo> page = VictiminfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<VictiminfoDTO> victiminfoDTOList = VictiminfoMapper.toDto(page.getContent());
        for (VictiminfoDTO f:victiminfoDTOList){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd);// 性别
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd);//民族
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd);//籍贯
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd);//婚姻状况
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd);// 政治面貌
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd);// 宗教信仰
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd); // 职业类别
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd);// 户籍地
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), f.getCardTypeStr());
            f.setCardTypeStr(dd == null ? "无数据" : dd);// 证件代码
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), f.getCountry());
            f.setCountryStr(dd == null ? "无数据" : dd);// 国籍
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), f.getEducationBg());
            f.setEducationBgStr(dd == null ? "无数据" : dd);// 学历
            dd = dictDetailService.transDict(DictEnum.SJZT.getDistName(), f.getStatusCd());
            f.setStatusCdStr(dd == null ? "无数据" : dd);// 数据状态

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", victiminfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(VictiminfoQueryCriteria criteria){
        return VictiminfoMapper.toDto(VictiminfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public VictiminfoDTO findById(String vicId) {
        log.info("详情社会治安管理/命案受害人信息--开始");
        if (StringUtils.isBlank(vicId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Victiminfo> Victiminfo = VictiminfoRepository.findById(vicId);
        ValidationUtil.isNull(Victiminfo,"Victiminfo","vicId",vicId);
        return VictiminfoMapper.toDto(Victiminfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VictiminfoDTO create(Victiminfo resources) {
        log.info("新增社会治安管理/命案受害人信息--开始");
        resources.setVicId(IdUtil.simpleUUID());
        resources.setCreator(SecurityUtils.getUsername());
        return VictiminfoMapper.toDto(VictiminfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Victiminfo resources) {
        log.info("修改社会治安管理/命案受害人信息--开始");
        if (StringUtils.isBlank(resources.getVicId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Victiminfo> optionalVictiminfo = VictiminfoRepository.findById(resources.getVicId());
        ValidationUtil.isNull( optionalVictiminfo,"Victiminfo","id",resources.getVicId());
        Victiminfo Victiminfo = optionalVictiminfo.get();
        Victiminfo.copy(resources);
        VictiminfoRepository.save(Victiminfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String vicId) {
        log.info("删除社会治安管理/命案受害人信息--开始");
        if (StringUtils.isBlank(vicId)){
            throw new BadRequestException("主键ID不能为空");
        }
        VictiminfoRepository.deleteById(vicId);
    }
}