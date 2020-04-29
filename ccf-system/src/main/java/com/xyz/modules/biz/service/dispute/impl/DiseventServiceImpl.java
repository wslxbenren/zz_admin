package com.xyz.modules.biz.service.dispute.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.actual.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.dispute.entity.Disevent;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.dispute.repo.DiseventRepository;
import com.xyz.modules.biz.service.dispute.DiseventService;
import com.xyz.modules.biz.service.dispute.dto.DiseventDTO;
import com.xyz.modules.biz.service.dispute.qo.DiseventQueryCriteria;
import com.xyz.modules.biz.service.dispute.mapper.DiseventMapper;
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
public class DiseventServiceImpl implements DiseventService {

    @Autowired
    private DiseventRepository DiseventRepository;

    @Autowired
    private DiseventMapper DiseventMapper;

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
    public Object queryAll(DiseventQueryCriteria criteria, Pageable pageable){
//        Page<Disevent> page = DiseventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(DiseventMapper::toDto));
    Page<Disevent> page=DiseventRepository.findAll(audit.genSpecification(criteria),pageable);
        List<DiseventDTO> diseventDTOS = DiseventMapper.toDto(page.getContent());

        for (DiseventDTO dto:diseventDTOS)
        {
            dto.setEventTypeStr(dictDetailService.transDict(DictEnum.SJLB.getDictId(),dto.getEventType()));//事件类型
            dto.setEventSizeStr(dictDetailService.transDict(DictEnum.SJGM.getDictId(),dto.getEventSize()));//事件规模
            dto.setPrinSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDictId(),dto.getPrinSex()));//性别
            dto.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(),dto.getResidence()));//现住地
            dto.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(),dto.getStatusCd()));//数据状态
            dto.setPrincardTypeStr(dictDetailService.transDict(DictEnum.ZJDM.getDictId(),dto.getPrincardType()));//证件类型
            dto.setPrinNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDictId(),dto.getPrinNation()));//民族
            dto.setPrinEducationStr(dictDetailService.transDict(DictEnum.XUE_LI.getDictId(),dto.getPrinEducation()));//证件类型
            dto.setPrinPertypeStr(dictDetailService.transDict(DictEnum.ZYDSRRYLB.getDictId(),dto.getPrinPertype()));//主要当事人人员类别
            dto.setCreator(userRepository.findById(Optional.ofNullable(dto.getCreator()).orElse("")).orElse(new User()).getUsername());
            dto.setOperName(userRepository.findById(Optional.ofNullable(dto.getOperName()).orElse("")).orElse(new User()).getUsername());

            dto.setUnitCodeStr(deptRepository.findNameByCode(dto.getUnitCode()));//涉及单位
        }
        Map map = new HashMap();
        map.put("content", diseventDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;

    }

    @Override
    @Transactional
    public Object queryAll(DiseventQueryCriteria criteria){
        return DiseventMapper.toDto(DiseventRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DiseventDTO findById(String eventId) {
        Optional<Disevent> Disevent = DiseventRepository.findById(eventId);
        ValidationUtil.isNull(Disevent,"Disevent","eventId",eventId);
        return DiseventMapper.toDto(Disevent.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DiseventDTO create(Disevent resources) {
        resources.setEventId(IdUtil.simpleUUID());
        return DiseventMapper.toDto(DiseventRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Disevent resources) {
        Optional<Disevent> optionalDisevent = DiseventRepository.findById(resources.getEventId());
        ValidationUtil.isNull( optionalDisevent,"Disevent","id",resources.getEventId());
        Disevent Disevent = optionalDisevent.get();
        Disevent.copy(resources);
        DiseventRepository.save(Disevent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String eventId) {
        DiseventRepository.deleteById(eventId);
    }
}