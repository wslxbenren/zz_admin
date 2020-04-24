package com.xyz.modules.biz.service.special.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.special.PsychosisPersonService;
import com.xyz.modules.biz.service.special.dto.PsychosisPersonDTO;
import com.xyz.modules.biz.service.special.entity.PsychosisPerson;
import com.xyz.modules.biz.service.special.mapper.PsychosisPersonMapper;
import com.xyz.modules.biz.service.special.qo.PsychosisPersonQueryCriteria;
import com.xyz.modules.biz.service.special.repo.PsychosisPersonRepository;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import io.netty.util.internal.StringUtil;
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
 * @author 刘鑫
 * @date 2020-04-10
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PsychosisPersonServiceImpl implements PsychosisPersonService {

    @Autowired
    private PsychosisPersonRepository PsychosisPersonRepository;

    @Resource
    private PsychosisPersonMapper PsychosisPersonMapper;

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
    public Object queryAll(PsychosisPersonQueryCriteria criteria, Pageable pageable) {
        log.debug("********** 条件查询 PsychosisPerson 列表-分页  **********");
        Page<PsychosisPerson> page = PsychosisPersonRepository.findAll(audit.genSpecification(criteria), pageable);
        List<PsychosisPersonDTO> psychosisPersonDTOS = PsychosisPersonMapper.toDto(page.getContent());
        for (PsychosisPersonDTO mid : psychosisPersonDTOS) {
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex()));// 性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation()));//民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag()));//婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag()));// 政治面貌
            mid.setEduLevelStr(dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEduLevel()));  // 文化程度
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType()));// 宗教信仰
            mid.setSourceIncomeStr(dictDetailService.transDict(DictEnum.JTQK.getDistName(), mid.getSourceIncome()));
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setDiagnoseTypeStr(dictDetailService.transDict(DictEnum.MQZDLX.getDistName(), mid.getDiagnoseType()));
            mid.setTreatFlagStr(dictDetailService.transDict(DictEnum.ZLQK.getDistName(), mid.getTreatFlag()));
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getResidence()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
            mid.setServicePlaceCodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
            if (!StringUtil.isNullOrEmpty(mid.getJoinManager())) {
                mid.setJoinManagerArr(mid.getJoinManager().replace('"', ' ').replaceAll(" ", "").split(","));
                mid.setJoinManagerStr(dictDetailService.getLabelByValues(DictEnum.CYGLRY.getDictId(), mid.getJoinManagerArr()));
            }
            if (!StringUtil.isNullOrEmpty(mid.getHelpeFlag())) {
                mid.setHelpeFlagArr(mid.getHelpeFlag().replace('"', ' ').replaceAll(" ", "").split(","));
                mid.setHelpeFlagStr(dictDetailService.getLabelByValues(DictEnum.BFQK.getDictId(), mid.getHelpeFlagArr()));
            }
            if (!StringUtil.isNullOrEmpty(mid.getInhospitalReason())) {
                mid.setInhospitalReasonArr(mid.getInhospitalReason().replace('"', ' ')
                        .replaceAll(" ", "").split(","));
                mid.setInhospitalReasonStr(dictDetailService.getLabelByValues(DictEnum.SSZYZLYY.getDictId(), mid.getInhospitalReasonArr()));
            }

            mid.setIsBasiclivingStr(ConstEnum.getBoolean(mid.getIsBasicliving()));
            mid.setIsTroubleStr(ConstEnum.getBoolean(mid.getIsTrouble()));
            mid.setRiskLevelStr(dictDetailService.transDict(DictEnum.MQWXXPGDJ.getDictId(), mid.getRiskLevel()));
        }
        Map map = new HashMap();
        map.put("content", psychosisPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(PsychosisPersonQueryCriteria criteria) {
        log.debug("********** 条件查询 PsychosisPerson 列表   **********");
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public PsychosisPersonDTO findById(String psychosisId) {
        log.debug("**********  查询 PsychosisPerson 详情   **********");
        Optional<PsychosisPerson> PsychosisPerson = PsychosisPersonRepository.findById(psychosisId);
        ValidationUtil.isNull(PsychosisPerson, "PsychosisPerson", "psychosisId", psychosisId);
        return PsychosisPersonMapper.toDto(PsychosisPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PsychosisPersonDTO create(PsychosisPerson resources) {
        log.debug("**********  新增 PsychosisPerson  **********");
        resources.setPsychosisId(IdUtil.simpleUUID());
        if (PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null) {
            throw new EntityExistException(PsychosisPerson.class, "identity_num", resources.getIdentityNum());
        }
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PsychosisPerson resources) {
        log.debug("**********  修改 PsychosisPerson  **********");
        Optional<PsychosisPerson> optionalPsychosisPerson = PsychosisPersonRepository.findById(resources.getPsychosisId());
        ValidationUtil.isNull(optionalPsychosisPerson, "PsychosisPerson", "id", resources.getPsychosisId());
        PsychosisPerson PsychosisPerson = optionalPsychosisPerson.get();
        PsychosisPerson psychosisPerson = PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if (psychosisPerson != null && !psychosisPerson.getPsychosisId().equals(PsychosisPerson.getPsychosisId())) {
            log.debug("********** PsychosisPerson identity_num 重复 修改失败 **********");
            throw new EntityExistException(PsychosisPerson.class, "identity_num", resources.getIdentityNum());
        }
        PsychosisPerson.copy(resources);
        PsychosisPersonRepository.save(PsychosisPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String psychosisId) {
        log.debug("**********  删除 PsychosisPerson  **********");
        PsychosisPersonRepository.deleteById(psychosisId);
    }
}