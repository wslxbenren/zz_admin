package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.BizSecurHomicidebaseinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizSecurHomicidebaseinfoRepository;
import com.xyz.modules.biz.service.BizSecurHomicidebaseinfoService;
import com.xyz.modules.biz.service.dto.BizSecurHomicidebaseinfoDTO;
import com.xyz.modules.biz.service.dto.BizSecurHomicidebaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSecurHomicidebaseinfoMapper;
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
public class BizSecurHomicidebaseinfoServiceImpl implements BizSecurHomicidebaseinfoService {

    @Autowired
    private BizSecurHomicidebaseinfoRepository bizSecurHomicidebaseinfoRepository;

    @Autowired
    private BizSecurHomicidebaseinfoMapper bizSecurHomicidebaseinfoMapper;

    @Override
    @Transactional
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria, Pageable pageable){
        Page<BizSecurHomicidebaseinfo> page = bizSecurHomicidebaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSecurHomicidebaseinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria){
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSecurHomicidebaseinfoDTO findById(String caseId) {
        Optional<BizSecurHomicidebaseinfo> bizSecurHomicidebaseinfo = bizSecurHomicidebaseinfoRepository.findById(caseId);
        ValidationUtil.isNull(bizSecurHomicidebaseinfo,"BizSecurHomicidebaseinfo","caseId",caseId);
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurHomicidebaseinfoDTO create(BizSecurHomicidebaseinfo resources) {
        resources.setCaseId(IdUtil.simpleUUID()); 
        if(bizSecurHomicidebaseinfoRepository.findByCaseCode(resources.getCaseCode()) != null){
            throw new EntityExistException(BizSecurHomicidebaseinfo.class,"case_code",resources.getCaseCode());
        }
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurHomicidebaseinfo resources) {
        Optional<BizSecurHomicidebaseinfo> optionalBizSecurHomicidebaseinfo = bizSecurHomicidebaseinfoRepository.findById(resources.getCaseId());
        ValidationUtil.isNull( optionalBizSecurHomicidebaseinfo,"BizSecurHomicidebaseinfo","id",resources.getCaseId());
        BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo = optionalBizSecurHomicidebaseinfo.get();
        BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo1  = bizSecurHomicidebaseinfoRepository.findByCaseCode(resources.getCaseCode());
        if(bizSecurHomicidebaseinfo1 != null && !bizSecurHomicidebaseinfo1.getCaseId().equals(bizSecurHomicidebaseinfo.getCaseId())){
            throw new EntityExistException(BizSecurHomicidebaseinfo.class,"case_code",resources.getCaseCode());
        }
        bizSecurHomicidebaseinfo.copy(resources);
        bizSecurHomicidebaseinfoRepository.save(bizSecurHomicidebaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String caseId) {
        bizSecurHomicidebaseinfoRepository.deleteById(caseId);
    }
}