package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.BizSchoolCaseinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizSchoolCaseinfoRepository;
import com.xyz.modules.biz.service.BizSchoolCaseinfoService;
import com.xyz.modules.biz.service.dto.BizSchoolCaseinfoDTO;
import com.xyz.modules.biz.service.dto.BizSchoolCaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSchoolCaseinfoMapper;
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
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizSchoolCaseinfoServiceImpl implements BizSchoolCaseinfoService {

    @Autowired
    private BizSchoolCaseinfoRepository bizSchoolCaseinfoRepository;

    @Autowired
    private BizSchoolCaseinfoMapper bizSchoolCaseinfoMapper;

    @Override
    public Object queryAll(BizSchoolCaseinfoQueryCriteria criteria, Pageable pageable){
        Page<BizSchoolCaseinfo> page = bizSchoolCaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSchoolCaseinfoMapper::toDto));
    }

    @Override
    public Object queryAll(BizSchoolCaseinfoQueryCriteria criteria){
        return bizSchoolCaseinfoMapper.toDto(bizSchoolCaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSchoolCaseinfoDTO findById(String caseId) {
        Optional<BizSchoolCaseinfo> bizSchoolCaseinfo = bizSchoolCaseinfoRepository.findById(caseId);
        ValidationUtil.isNull(bizSchoolCaseinfo,"BizSchoolCaseinfo","caseId",caseId);
        return bizSchoolCaseinfoMapper.toDto(bizSchoolCaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSchoolCaseinfoDTO create(BizSchoolCaseinfo resources) {
        resources.setCaseId(IdUtil.simpleUUID()); 
        return bizSchoolCaseinfoMapper.toDto(bizSchoolCaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSchoolCaseinfo resources) {
        Optional<BizSchoolCaseinfo> optionalBizSchoolCaseinfo = bizSchoolCaseinfoRepository.findById(resources.getCaseId());
        ValidationUtil.isNull( optionalBizSchoolCaseinfo,"BizSchoolCaseinfo","id",resources.getCaseId());
        BizSchoolCaseinfo bizSchoolCaseinfo = optionalBizSchoolCaseinfo.get();
        bizSchoolCaseinfo.copy(resources);
        bizSchoolCaseinfoRepository.save(bizSchoolCaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String caseId) {
        bizSchoolCaseinfoRepository.deleteById(caseId);
    }
}