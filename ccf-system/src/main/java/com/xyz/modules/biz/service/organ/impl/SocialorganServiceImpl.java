package com.xyz.modules.biz.service.organ.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.organ.entity.Socialorgan;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.organ.repo.SocialorganRepository;
import com.xyz.modules.biz.service.organ.SocialorganService;
import com.xyz.modules.biz.service.organ.dto.SocialorganDTO;
import com.xyz.modules.biz.service.organ.qo.SocialorganQueryCriteria;
import com.xyz.modules.biz.service.organ.mapper.SocialorganMapper;
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
public class SocialorganServiceImpl implements SocialorganService {

    @Autowired
    private SocialorganRepository SocialorganRepository;

    @Autowired
    private SocialorganMapper SocialorganMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private AuditSpecification audit;




    @Override
    @Transactional
    public Object queryAll(SocialorganQueryCriteria criteria, Pageable pageable){
//        Page<Socialorgan> page = SocialorganRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(SocialorganMapper::toDto));

    Page<Socialorgan> page=SocialorganRepository.findAll(audit.genSpecification(criteria),pageable);
        List<SocialorganDTO> socialorganDTOList=SocialorganMapper.toDto(page.getContent());
        for(SocialorganDTO dto:socialorganDTOList){

                String dd=dictDetailService.transDict(DictEnum.GZCD.getDistName(),dto.getAttention());
                dto.setAttentionStr(dd==null?"无数据":dd);//关注程度

                dd=dictDetailService.transDict(DictEnum.GZCD.getDistName(),dto.getAddrcode());
                dto.setAttentionStr(dd==null?"无数据":dd);

                dd=dictDetailService.transDict(DictEnum.SJZT.getDistName(),dto.getStatusCd());
                dto.setStatusCdStr(dd==null?"无数据":dd);

            dd = deptRepository.findNameByCode(dto.getUnitCode());
            dto.setUnitCodeStr(dd);

        }
        Map map=new HashMap();
        map.put("content", socialorganDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());

    return map;
    }

    @Override
    @Transactional
    public Object queryAll(SocialorganQueryCriteria criteria){
        return SocialorganMapper.toDto(SocialorganRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public SocialorganDTO findById(String sociaId) {
        Optional<Socialorgan> Socialorgan = SocialorganRepository.findById(sociaId);
        ValidationUtil.isNull(Socialorgan,"Socialorgan","sociaId",sociaId);
        return SocialorganMapper.toDto(Socialorgan.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SocialorganDTO create(Socialorgan resources) {
        resources.setSociaId(IdUtil.simpleUUID());
        resources.setCreator(SecurityUtils.getUsername());
        return SocialorganMapper.toDto(SocialorganRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Socialorgan resources) {
        Optional<Socialorgan> optionalSocialorgan = SocialorganRepository.findById(resources.getSociaId());
        ValidationUtil.isNull( optionalSocialorgan,"Socialorgan","id",resources.getSociaId());
        Socialorgan Socialorgan = optionalSocialorgan.get();
        Socialorgan.copy(resources);
        SocialorganRepository.save(Socialorgan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String sociaId) {
        SocialorganRepository.deleteById(sociaId);
    }
}