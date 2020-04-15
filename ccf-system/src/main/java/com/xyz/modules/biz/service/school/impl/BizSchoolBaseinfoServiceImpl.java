package com.xyz.modules.biz.service.school.impl;

import com.xyz.modules.biz.service.school.entity.BizSchoolBaseinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.school.repo.BizSchoolBaseinfoRepository;
import com.xyz.modules.biz.service.school.BizSchoolBaseinfoService;
import com.xyz.modules.biz.service.school.dto.BizSchoolBaseinfoDTO;
import com.xyz.modules.biz.service.school.qo.BizSchoolBaseinfoQueryCriteria;
import com.xyz.modules.biz.service.school.mapper.BizSchoolBaseinfoMapper;
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
public class BizSchoolBaseinfoServiceImpl implements BizSchoolBaseinfoService {

    @Autowired
    private BizSchoolBaseinfoRepository bizSchoolBaseinfoRepository;

    @Autowired
    private BizSchoolBaseinfoMapper bizSchoolBaseinfoMapper;

    @Override
    @Transactional
    public Object queryAll(BizSchoolBaseinfoQueryCriteria criteria, Pageable pageable){
        Page<BizSchoolBaseinfo> page = bizSchoolBaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bizSchoolBaseinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(BizSchoolBaseinfoQueryCriteria criteria){
        return bizSchoolBaseinfoMapper.toDto(bizSchoolBaseinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public BizSchoolBaseinfoDTO findById(String baseId) {
        Optional<BizSchoolBaseinfo> bizSchoolBaseinfo = bizSchoolBaseinfoRepository.findById(baseId);
        ValidationUtil.isNull(bizSchoolBaseinfo,"BizSchoolBaseinfo","baseId",baseId);
        return bizSchoolBaseinfoMapper.toDto(bizSchoolBaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSchoolBaseinfoDTO create(BizSchoolBaseinfo resources) {
        resources.setBaseId(IdUtil.simpleUUID()); 
        return bizSchoolBaseinfoMapper.toDto(bizSchoolBaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSchoolBaseinfo resources) {
        Optional<BizSchoolBaseinfo> optionalBizSchoolBaseinfo = bizSchoolBaseinfoRepository.findById(resources.getBaseId());
        ValidationUtil.isNull( optionalBizSchoolBaseinfo,"BizSchoolBaseinfo","id",resources.getBaseId());
        BizSchoolBaseinfo bizSchoolBaseinfo = optionalBizSchoolBaseinfo.get();
        bizSchoolBaseinfo.copy(resources);
        bizSchoolBaseinfoRepository.save(bizSchoolBaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String baseId) {
        bizSchoolBaseinfoRepository.deleteById(baseId);
    }
}