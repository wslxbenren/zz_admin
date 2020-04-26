package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.secur.entity.Suspectinfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.secur.repo.SuspectinfoRepository;
import com.xyz.modules.biz.service.secur.SuspectinfoService;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoDTO;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.SuspectinfoMapper;
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
 * 功能模块：社会治安管理/命案犯罪嫌疑人信息
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuspectinfoServiceImpl implements SuspectinfoService {

    @Autowired
    private SuspectinfoRepository SuspectinfoRepository;

    @Autowired
    private SuspectinfoMapper SuspectinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/命案犯罪嫌疑人信息--开始");
        Page<Suspectinfo> page = SuspectinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<SuspectinfoDTO> suspectinfoDTOList = SuspectinfoMapper.toDto(page.getContent());
        for (SuspectinfoDTO f:suspectinfoDTOList){
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
        map.put("content", suspectinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria){
        return SuspectinfoMapper.toDto(SuspectinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public SuspectinfoDTO findById(String suspId) {
        log.info("详情社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> Suspectinfo = SuspectinfoRepository.findById(suspId);
        ValidationUtil.isNull(Suspectinfo,"Suspectinfo","suspId",suspId);
        return SuspectinfoMapper.toDto(Suspectinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectinfoDTO create(Suspectinfo resources) {
        log.info("新增社会治安管理/命案犯罪嫌疑人信息--开始");
        resources.setSuspId(IdUtil.simpleUUID());
        return SuspectinfoMapper.toDto(SuspectinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Suspectinfo resources) {
        log.info("修改社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(resources.getSuspId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> optionalSuspectinfo = SuspectinfoRepository.findById(resources.getSuspId());
        ValidationUtil.isNull( optionalSuspectinfo,"Suspectinfo","id",resources.getSuspId());
        Suspectinfo Suspectinfo = optionalSuspectinfo.get();
        Suspectinfo.copy(resources);
        SuspectinfoRepository.save(Suspectinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String suspId) {
        log.info("删除社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        SuspectinfoRepository.deleteById(suspId);
    }
}