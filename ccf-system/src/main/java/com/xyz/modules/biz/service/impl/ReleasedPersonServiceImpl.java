package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.ReleasedPerson;
import com.xyz.modules.biz.repository.ReleasedPersonRepository;
import com.xyz.modules.biz.service.ReleasedPersonService;
import com.xyz.modules.biz.service.dto.ReleasedPersonDTO;
import com.xyz.modules.biz.service.dto.ReleasedPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.ReleasedPersonMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
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
 * @author 刘鑫
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReleasedPersonServiceImpl implements ReleasedPersonService {

    @Autowired
    private ReleasedPersonRepository ReleasedPersonRepository;

    @Resource
    private ReleasedPersonMapper ReleasedPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Override
    public Object queryAll(ReleasedPersonQueryCriteria criteria, Pageable pageable){
        Page<ReleasedPerson> page = ReleasedPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<ReleasedPersonDTO> releasedPersonDTOS = ReleasedPersonMapper.toDto(page.getContent());
        for (ReleasedPersonDTO f:releasedPersonDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd.getLabel());// 性别
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd.getLabel());//民族
            dd = dictDetailService.findByValueAndPName(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd.getLabel());//籍贯
            dd = dictDetailService.findByValueAndPName(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd.getLabel());//婚姻状况
            dd = dictDetailService.findByValueAndPName(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd.getLabel());// 政治面貌
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), f.getEduLevel());
            f.setEduLevelStr(dd == null ? "无数据" : dd.getLabel());  // 文化程度
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd.getLabel());// 宗教信仰
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd.getLabel()); // 职业类别
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd.getLabel());// 户籍地

            dd = dictDetailService.findByValueAndPName(DictEnum.WXXPGLX.getDistName(), f.getRiskType());
            f.setRiskTypeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XJQK.getDistName(), f.getJoinFlag());
            f.setJoinFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.AZQK.getDistName(), f.getArrangeFlag());
            f.setArrangeFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.BFQK.getDistName(), f.getHelpeComment());
            f.setHelpeCommentStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", releasedPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    public Object queryAll(ReleasedPersonQueryCriteria criteria){
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public ReleasedPersonDTO findById(String releasedId) {
        Optional<ReleasedPerson> ReleasedPerson = ReleasedPersonRepository.findById(releasedId);
        ValidationUtil.isNull(ReleasedPerson,"ReleasedPerson","releasedId",releasedId);
        return ReleasedPersonMapper.toDto(ReleasedPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReleasedPersonDTO create(ReleasedPerson resources) {
        resources.setReleasedId(IdUtil.simpleUUID()); 
        if(ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReleasedPerson resources) {
        Optional<ReleasedPerson> optionalReleasedPerson = ReleasedPersonRepository.findById(resources.getReleasedId());
        ValidationUtil.isNull( optionalReleasedPerson,"ReleasedPerson","id",resources.getReleasedId());
        ReleasedPerson ReleasedPerson = optionalReleasedPerson.get();
        ReleasedPerson releasedPerson = ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(releasedPerson != null && !releasedPerson.getReleasedId().equals(ReleasedPerson.getReleasedId())){
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        ReleasedPerson.copy(resources);
        ReleasedPersonRepository.save(ReleasedPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String releasedId) {
        ReleasedPersonRepository.deleteById(releasedId);
    }
}