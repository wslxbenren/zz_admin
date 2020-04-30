package com.xyz.modules.biz.service.route.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.route.repo.KeypersoninfoRepository;
import com.xyz.modules.biz.service.route.KeypersoninfoService;
import com.xyz.modules.biz.service.route.dto.KeypersoninfoDTO;
import com.xyz.modules.biz.service.route.qo.KeypersoninfoQueryCriteria;
import com.xyz.modules.biz.service.route.mapper.KeypersoninfoMapper;
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
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class KeypersoninfoServiceImpl implements KeypersoninfoService {

    @Autowired
    private KeypersoninfoRepository KeypersoninfoRepository;

    @Autowired
    private KeypersoninfoMapper KeypersoninfoMapper;

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
    public Object queryAll(KeypersoninfoQueryCriteria criteria, Pageable pageable){
//        Page<Keypersoninfo> page = KeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
//                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);


        Page<Keypersoninfo> page = KeypersoninfoRepository.findAll(audit.genSpecification(criteria), pageable);
        List<KeypersoninfoDTO> KeypersoninfoDTOList = KeypersoninfoMapper.toDto(page.getContent());
        for (KeypersoninfoDTO dto:KeypersoninfoDTOList  )
        {
            dto.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(), dto.getPersonSex()));//性别
            dto.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(),dto.getNation()));//民族
            dto.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(),dto.getNativeInfo()));//籍贯
            dto.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDictId(),dto.getMarriageFlag()));//婚姻状况
            dto.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDictId(),dto.getPartyFlag()));//政治面貌
            dto.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(),dto.getEducationBg()));//学历
            dto.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZJXY.getDictId(),dto.getFaithType()));//宗教信仰
            dto.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(),dto.getVocationCode()));//职业类型
            dto.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(),dto.getRegisteredPlace()));//户籍地
            dto.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(),dto.getResidence()));//现住地
            dto.setServiceAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(),dto.getServiceAddrcode()));//服务处所
            dto.setHazardLevelStr(dictDetailService.transDict(DictEnum.WXCD.getDictId(),dto.getHazardLevel()));//危害程度
            dto.setUnitCodeStr(deptRepository.findNameByCode(dto.getUnitCode()));//所属单位
            dto.setCreator(userRepository.findById(Optional.ofNullable(dto.getCreator()).orElse("")).orElse(new User()).getUsername());
            dto.setOperName(userRepository.findById(Optional.ofNullable(dto.getOperName()).orElse("")).orElse(new User()).getUsername());

        }


        Map map = new HashMap();
        map.put("content", KeypersoninfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(KeypersoninfoQueryCriteria criteria){
        return KeypersoninfoMapper.toDto(KeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public KeypersoninfoDTO findById(String keyId) {
        Optional<Keypersoninfo> Keypersoninfo = KeypersoninfoRepository.findById(keyId);
        ValidationUtil.isNull(Keypersoninfo,"Keypersoninfo","keyId",keyId);
        return KeypersoninfoMapper.toDto(Keypersoninfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KeypersoninfoDTO create(Keypersoninfo resources) {
        resources.setKeyId(IdUtil.simpleUUID());
//        resources.setCreator(SecurityUtils.getUsername());
        return KeypersoninfoMapper.toDto(KeypersoninfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Keypersoninfo resources) {
        Optional<Keypersoninfo> optionalKeypersoninfo = KeypersoninfoRepository.findById(resources.getKeyId());
        ValidationUtil.isNull( optionalKeypersoninfo,"Keypersoninfo","id",resources.getKeyId());
        Keypersoninfo Keypersoninfo = optionalKeypersoninfo.get();
        //resources.setCreator(SecurityUtils.getUsername());
        Keypersoninfo.copy(resources);
        KeypersoninfoRepository.save(Keypersoninfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String keyId) {
        KeypersoninfoRepository.deleteById(keyId);
    }
}