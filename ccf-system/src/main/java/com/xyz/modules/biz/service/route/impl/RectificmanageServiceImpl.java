package com.xyz.modules.biz.service.route.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.route.dto.KeypersoninfoDTO;
import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.route.repo.RectificmanageRepository;
import com.xyz.modules.biz.service.route.RectificmanageService;
import com.xyz.modules.biz.service.route.dto.RectificmanageDTO;
import com.xyz.modules.biz.service.route.qo.RectificmanageQueryCriteria;
import com.xyz.modules.biz.service.route.mapper.RectificmanageMapper;
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
public class RectificmanageServiceImpl implements RectificmanageService {

    @Autowired
    private RectificmanageRepository RectificmanageRepository;

    @Autowired
    private RectificmanageMapper RectificmanageMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private AuditSpecification audit;



    @Override
    @Transactional
    public Object queryAll(RectificmanageQueryCriteria criteria, Pageable pageable){
//        Page<Rectificmanage> page = RectificmanageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(RectificmanageMapper::toDto));
        Page<Rectificmanage> page=RectificmanageRepository.findAll(audit.genSpecification(criteria), pageable);
        List<RectificmanageDTO> RectificmanageDTOList = RectificmanageMapper.toDto(page.getContent());
        for (RectificmanageDTO dto:RectificmanageDTOList)
        {
           String dd = deptRepository.findNameByCode(dto.getResponUnit());
            dto.setResponUnitStr(dd);//责任单位

             dd = deptRepository.findNameByCode(dto.getAssistUnit());
            dto.setAssistUnitStr(dd);//协助单位

            dd = deptRepository.findNameByCode(dto.getAssistUnit());
            dto.setAssistUnitStr(dd);//协助单位

            dd = dictDetailService.transDict(DictEnum.SJZT.getDistName(),dto.getStatusCd());
            dto.setStatusCdStr(dd==null?"无数据":dd);//数据状态

            dd = deptRepository.findNameByCode(dto.getUnitCode());
            dto.setUnitCodeStr(dd);//所属单位
        }


        Map map = new HashMap();
        map.put("content", RectificmanageDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;


    }

    @Override
    @Transactional
    public Object queryAll(RectificmanageQueryCriteria criteria){
        return RectificmanageMapper.toDto(RectificmanageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public RectificmanageDTO findById(String rectId) {
        Optional<Rectificmanage> Rectificmanage = RectificmanageRepository.findById(rectId);
        ValidationUtil.isNull(Rectificmanage,"Rectificmanage","rectId",rectId);
        return RectificmanageMapper.toDto(Rectificmanage.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RectificmanageDTO create(Rectificmanage resources) {
        resources.setRectId(IdUtil.simpleUUID());
        resources.setCreator(SecurityUtils.getUsername());
        return RectificmanageMapper.toDto(RectificmanageRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rectificmanage resources) {
        Optional<Rectificmanage> optionalRectificmanage = RectificmanageRepository.findById(resources.getRectId());
        ValidationUtil.isNull( optionalRectificmanage,"Rectificmanage","id",resources.getRectId());
        Rectificmanage Rectificmanage = optionalRectificmanage.get();
        Rectificmanage.copy(resources);
        RectificmanageRepository.save(Rectificmanage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rectId) {
        RectificmanageRepository.deleteById(rectId);
    }
}