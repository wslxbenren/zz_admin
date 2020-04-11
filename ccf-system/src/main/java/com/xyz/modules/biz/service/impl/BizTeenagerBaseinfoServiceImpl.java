package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.BizTeenagerBaseinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.BizTeenagerBaseinfoRepository;
import com.xyz.modules.biz.service.BizTeenagerBaseinfoService;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoDTO;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizTeenagerBaseinfoMapper;
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
public class BizTeenagerBaseinfoServiceImpl implements BizTeenagerBaseinfoService {

    @Autowired
    private BizTeenagerBaseinfoRepository bizTeenagerBaseinfoRepository;

    @Autowired
    private BizTeenagerBaseinfoMapper bizTeenagerBaseinfoMapper;

    @Override
    @Transactional
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria, Pageable pageable){
        Page<BizTeenagerBaseinfo> page = bizTeenagerBaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizTeenagerBaseinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria){
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizTeenagerBaseinfoDTO findById(String teenId) {
        Optional<BizTeenagerBaseinfo> bizTeenagerBaseinfo = bizTeenagerBaseinfoRepository.findById(teenId);
        ValidationUtil.isNull(bizTeenagerBaseinfo,"BizTeenagerBaseinfo","teenId",teenId);
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizTeenagerBaseinfoDTO create(BizTeenagerBaseinfo resources) {
        resources.setTeenId(IdUtil.simpleUUID()); 
        if(bizTeenagerBaseinfoRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(BizTeenagerBaseinfo.class,"identity_num",resources.getIdentityNum());
        }
        return bizTeenagerBaseinfoMapper.toDto(bizTeenagerBaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizTeenagerBaseinfo resources) {
        Optional<BizTeenagerBaseinfo> optionalBizTeenagerBaseinfo = bizTeenagerBaseinfoRepository.findById(resources.getTeenId());
        ValidationUtil.isNull( optionalBizTeenagerBaseinfo,"BizTeenagerBaseinfo","id",resources.getTeenId());
        BizTeenagerBaseinfo bizTeenagerBaseinfo = optionalBizTeenagerBaseinfo.get();
        BizTeenagerBaseinfo bizTeenagerBaseinfo1 = null;
        bizTeenagerBaseinfo1 = bizTeenagerBaseinfoRepository.findByIdentityNum(resources.getIdentityNum());
        if(bizTeenagerBaseinfo1 != null && !bizTeenagerBaseinfo1.getTeenId().equals(bizTeenagerBaseinfo.getTeenId())){
            throw new EntityExistException(BizTeenagerBaseinfo.class,"identity_num",resources.getIdentityNum());
        }
        bizTeenagerBaseinfo.copy(resources);
        bizTeenagerBaseinfoRepository.save(bizTeenagerBaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String teenId) {
        bizTeenagerBaseinfoRepository.deleteById(teenId);
    }
}