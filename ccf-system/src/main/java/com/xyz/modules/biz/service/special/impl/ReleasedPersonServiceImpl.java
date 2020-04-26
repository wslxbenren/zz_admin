package com.xyz.modules.biz.service.special.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.special.entity.ReleasedPerson;
import com.xyz.modules.biz.service.special.repo.ReleasedPersonRepository;
import com.xyz.modules.biz.service.special.ReleasedPersonService;
import com.xyz.modules.biz.service.special.dto.ReleasedPersonDTO;
import com.xyz.modules.biz.service.special.qo.ReleasedPersonQueryCriteria;
import com.xyz.modules.biz.service.special.mapper.ReleasedPersonMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Slf4j
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

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(ReleasedPersonQueryCriteria criteria, Pageable pageable){
        log.debug("**********条件查询 ReleasedPerson 列表-分页**********");
        if (criteria.getNativeInfo() != null & criteria.getNativeInfo() != "") {
            String addrPrefix = ConstEnum.genAddrPrefix(criteria.getNativeInfo());
            if(addrPrefix.length() != 6) {
                criteria.setNativeInfoWithDownGrade(dictDetailService.addrWithDownGrade(addrPrefix, DictEnum.ADDRESS.getDictId()));
            } else {
                criteria.setNativeInfoWithDownGrade(new ArrayList<String>() {{ add(addrPrefix); }});
            }
        }
        Page<ReleasedPerson> page = ReleasedPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<ReleasedPersonDTO> releasedPersonDTOS = ReleasedPersonMapper.toDto(page.getContent());
        for (ReleasedPersonDTO mid:releasedPersonDTOS){
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(), mid.getPersonSex()));// 性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(), mid.getNation()));//民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDictId(), mid.getMarriageFlag()));//婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDictId(), mid.getPartyFlag()));// 政治面貌
            mid.setEduLevelStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(), mid.getEduLevel()));  // 文化程度
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDictId(), mid.getFaithType()));// 宗教信仰
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setRiskTypeStr(dictDetailService.transDict(DictEnum.WXXPGLX.getDictId(), mid.getRiskType()));
            mid.setJoinFlagStr(dictDetailService.transDict(DictEnum.XJQK.getDictId(), mid.getJoinFlag()));
            mid.setArrangeFlagStr(dictDetailService.transDict(DictEnum.AZQK.getDictId(), mid.getArrangeFlag()));
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), mid.getStatusCd()));
            mid.setServicePlaceCodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
            mid.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getResidence()));
            mid.setIsAgainStr(ConstEnum.getBoolean(mid.getIsAgain()));
            mid.setIsPedigreeStr(ConstEnum.getBoolean(mid.getIsPedigree()));
            mid.setChargeCommentsStr(dictDetailService.transDict(DictEnum.YZM.getDictId(), mid.getChargeComments()));

        }
        Map map = new HashMap();
        map.put("content", releasedPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(ReleasedPersonQueryCriteria criteria){
        log.debug("**********条件查询 ReleasedPerson 列表 **********");
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public ReleasedPersonDTO findById(String releasedId) {
        log.debug("********** 查询 ReleasedPerson 查询 **********");
        Optional<ReleasedPerson> ReleasedPerson = ReleasedPersonRepository.findById(releasedId);
        ValidationUtil.isNull(ReleasedPerson,"ReleasedPerson","releasedId",releasedId);
        return ReleasedPersonMapper.toDto(ReleasedPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReleasedPersonDTO create(ReleasedPerson resources) {
        log.debug("********** 新增 ReleasedPerson   **********");
        resources.setReleasedId(IdUtil.simpleUUID()); 
        if(ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            log.debug("********** ReleasedPerson identity_num 重复 新增失败 **********");
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        return ReleasedPersonMapper.toDto(ReleasedPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReleasedPerson resources) {
        log.debug("********** 修改 ReleasedPerson   **********");
        Optional<ReleasedPerson> optionalReleasedPerson = ReleasedPersonRepository.findById(resources.getReleasedId());
        ValidationUtil.isNull( optionalReleasedPerson,"ReleasedPerson","id",resources.getReleasedId());
        ReleasedPerson ReleasedPerson = optionalReleasedPerson.get();
        ReleasedPerson releasedPerson = ReleasedPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(releasedPerson != null && !releasedPerson.getReleasedId().equals(ReleasedPerson.getReleasedId())){
            log.debug("********** ReleasedPerson identity_num 重复 修改失败 **********");
            throw new EntityExistException(ReleasedPerson.class,"identity_num",resources.getIdentityNum());
        }
        ReleasedPerson.copy(resources);
        ReleasedPersonRepository.save(ReleasedPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String releasedId) {
        log.debug("********** 删除 ReleasedPerson   **********");
        ReleasedPersonRepository.deleteById(releasedId);
    }
}