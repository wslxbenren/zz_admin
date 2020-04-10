package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Caseinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.CaseinfoRepository;
import com.xyz.modules.biz.service.CaseinfoService;
import com.xyz.modules.biz.service.dto.CaseinfoDTO;
import com.xyz.modules.biz.service.dto.CaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.CaseinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
public class CaseinfoServiceImpl implements CaseinfoService {

    @Autowired
    private CaseinfoRepository CaseinfoRepository;

    @Autowired
    private CaseinfoMapper CaseinfoMapper;

    @Override
    public Object queryAll(CaseinfoQueryCriteria criteria, Pageable pageable){
        Page<Caseinfo> page = CaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(CaseinfoMapper::toDto));
    }

    @Override
    public Object queryAll(CaseinfoQueryCriteria criteria){
        return CaseinfoMapper.toDto(CaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public CaseinfoDTO findById(String caseId) {
        Optional<Caseinfo> Caseinfo = CaseinfoRepository.findById(caseId);
        ValidationUtil.isNull(Caseinfo,"Caseinfo","caseId",caseId);
        return CaseinfoMapper.toDto(Caseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CaseinfoDTO create(Caseinfo resources) {
        resources.setCaseId(IdUtil.simpleUUID()); 
        return CaseinfoMapper.toDto(CaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Caseinfo resources) {
        Optional<Caseinfo> optionalCaseinfo = CaseinfoRepository.findById(resources.getCaseId());
        ValidationUtil.isNull( optionalCaseinfo,"Caseinfo","id",resources.getCaseId());
        Caseinfo Caseinfo = optionalCaseinfo.get();
        Caseinfo.copy(resources);
        CaseinfoRepository.save(Caseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String caseId) {
        CaseinfoRepository.deleteById(caseId);
    }
}