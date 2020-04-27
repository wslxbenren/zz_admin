package com.xyz.modules.biz.service.organ.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.organ.repo.NonpublicRepository;
import com.xyz.modules.biz.service.organ.NonpublicService;
import com.xyz.modules.biz.service.organ.dto.NonpublicDTO;
import com.xyz.modules.biz.service.organ.qo.NonpublicQueryCriteria;
import com.xyz.modules.biz.service.organ.mapper.NonpublicMapper;
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
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NonpublicServiceImpl implements NonpublicService {

    @Autowired
    private NonpublicRepository NonpublicRepository;

    @Autowired
    private NonpublicMapper NonpublicMapper;


    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public Object queryAll(NonpublicQueryCriteria criteria, Pageable pageable) {
//        Page<Nonpublic> page = NonpublicRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(NonpublicMapper::toDto));


        Page<Nonpublic> page = NonpublicRepository.findAll(audit.genSpecification(criteria), pageable);
        List<NonpublicDTO> nonpublicDTOList = NonpublicMapper.toDto(page.getContent());

        for (NonpublicDTO dto : nonpublicDTOList) {

            dto.setEntityTypeStr(dictDetailService.transDict(DictEnum.QYLX.getDictId(), dto.getEntityType()));//企业类型
            dto.setEntityTypeStr(dictDetailService.transDict(DictEnum.YHLX.getDictId(), dto.getSafetroubleType()));//安全隐患类型
            dto.setAttentionStr(dictDetailService.transDict(DictEnum.GZCD.getDictId(), dto.getAttention()));//关注程度
            dto.setStatusCd(dictDetailService.transDict(DictEnum.SJZT.getDictId(), dto.getStatusCd()));//数据状态
            dto.setUnitCodeStr(deptRepository.findNameByCode(dto.getUnitCode()));//所属单位

            dto.setIfConditionStr(ConstEnum.getBoolean(dto.getIfCondition()));
            dto.setIfUnionStr(ConstEnum.getBoolean(dto.getIfUnion()));
            dto.setIfDangerousStr(ConstEnum.getBoolean(dto.getIfDangerous()));
            dto.setIfOrganStr(ConstEnum.getBoolean(dto.getIfOrgan()));
            dto.setIfWomenfederStr(ConstEnum.getBoolean(dto.getIfWomenfeder()));
            dto.setIfYouthleagueStr(ConstEnum.getBoolean(dto.getIfYouthleague()));
            dto.setEntityTypeStr(dictDetailService.transDict(DictEnum.QYLB.getDictId(), dto.getEntityType()));
            dto.setLegalcardTypeStr(dictDetailService.transDict(DictEnum.ZJDM.getDictId(), dto.getLegalcardType()));
            dto.setSafetroubleTypeStr(dictDetailService.transDict(DictEnum.AQYHLX.getDictId(), dto.getSafetroubleType()));
            dto.setEntityAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), dto.getEntityAddrcode()));
            dto.setCreator(userRepository.findById(Optional.ofNullable(dto.getCreator()).orElse("")).orElse(new User()).getUsername());
            dto.setOperName(userRepository.findById(Optional.ofNullable(dto.getOperName()).orElse("")).orElse(new User()).getUsername());
        }

        Map map = new HashMap();
        map.put("content", nonpublicDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages", page.getTotalPages());
        return map;

    }

    @Override
    @Transactional
    public Object queryAll(NonpublicQueryCriteria criteria) {
        return NonpublicMapper.toDto(NonpublicRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public NonpublicDTO findById(String nonId) {
        Optional<Nonpublic> Nonpublic = NonpublicRepository.findById(nonId);
        ValidationUtil.isNull(Nonpublic, "Nonpublic", "nonId", nonId);
        return NonpublicMapper.toDto(Nonpublic.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NonpublicDTO create(Nonpublic resources) {
        resources.setNonId(IdUtil.simpleUUID());
        resources.setCreator(SecurityUtils.getUsername());
        return NonpublicMapper.toDto(NonpublicRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Nonpublic resources) {
        Optional<Nonpublic> optionalNonpublic = NonpublicRepository.findById(resources.getNonId());
        ValidationUtil.isNull(optionalNonpublic, "Nonpublic", "id", resources.getNonId());
        Nonpublic Nonpublic = optionalNonpublic.get();
        Nonpublic.copy(resources);
        NonpublicRepository.save(Nonpublic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String nonId) {
        NonpublicRepository.deleteById(nonId);
    }
}