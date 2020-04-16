package com.xyz.modules.biz.service.school.impl;

import com.xyz.modules.biz.service.school.dto.BizSchoolKeypersoninfoDTO;
import com.xyz.modules.biz.service.school.entity.BizSchoolKeypersoninfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.school.repo.BizSchoolKeypersoninfoRepository;
import com.xyz.modules.biz.service.school.BizSchoolKeypersoninfoService;
import com.xyz.modules.biz.service.school.qo.BizSchoolKeypersoninfoQueryCriteria;
import com.xyz.modules.biz.service.school.mapper.BizSchoolKeypersoninfoMapper;
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
public class BizSchoolKeypersoninfoServiceImpl implements BizSchoolKeypersoninfoService {

    @Autowired
    private BizSchoolKeypersoninfoRepository bizSchoolKeypersoninfoRepository;

    @Autowired
    private BizSchoolKeypersoninfoMapper bizSchoolKeypersoninfoMapper;

    @Override
    @Transactional
    public Object queryAll(BizSchoolKeypersoninfoQueryCriteria criteria, Pageable pageable){
        Page<BizSchoolKeypersoninfo> page = bizSchoolKeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSchoolKeypersoninfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(BizSchoolKeypersoninfoQueryCriteria criteria){
        return bizSchoolKeypersoninfoMapper.toDto(bizSchoolKeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSchoolKeypersoninfoDTO findById(String keyId) {
        Optional<BizSchoolKeypersoninfo> bizSchoolKeypersoninfo = bizSchoolKeypersoninfoRepository.findById(keyId);
        ValidationUtil.isNull(bizSchoolKeypersoninfo,"BizSchoolKeypersoninfo","keyId",keyId);
        return bizSchoolKeypersoninfoMapper.toDto(bizSchoolKeypersoninfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSchoolKeypersoninfoDTO create(BizSchoolKeypersoninfo resources) {
        resources.setKeyId(IdUtil.simpleUUID()); 
        return bizSchoolKeypersoninfoMapper.toDto(bizSchoolKeypersoninfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSchoolKeypersoninfo resources) {
        Optional<BizSchoolKeypersoninfo> optionalBizSchoolKeypersoninfo = bizSchoolKeypersoninfoRepository.findById(resources.getKeyId());
        ValidationUtil.isNull( optionalBizSchoolKeypersoninfo,"BizSchoolKeypersoninfo","id",resources.getKeyId());
        BizSchoolKeypersoninfo bizSchoolKeypersoninfo = optionalBizSchoolKeypersoninfo.get();
        bizSchoolKeypersoninfo.copy(resources);
        bizSchoolKeypersoninfoRepository.save(bizSchoolKeypersoninfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String keyId) {
        bizSchoolKeypersoninfoRepository.deleteById(keyId);
    }
}