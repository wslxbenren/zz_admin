package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.Leftbehind;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.LeftbehindRepository;
import com.xyz.modules.biz.service.LeftbehindService;
import com.xyz.modules.biz.service.dto.LeftbehindDTO;
import com.xyz.modules.biz.service.dto.LeftbehindQueryCriteria;
import com.xyz.modules.biz.service.mapper.LeftbehindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author xjh
* @date 2020-04-08
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LeftbehindServiceImpl implements LeftbehindService {

    @Autowired
    private LeftbehindRepository LeftbehindRepository;

    @Autowired
    private LeftbehindMapper LeftbehindMapper;

    @Override
    public Object queryAll(LeftbehindQueryCriteria criteria, Pageable pageable){
        Page<Leftbehind> page = LeftbehindRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(LeftbehindMapper::toDto));
    }

    @Override
    public Object queryAll(LeftbehindQueryCriteria criteria){
        return LeftbehindMapper.toDto(LeftbehindRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public LeftbehindDTO findById(String leftId) {
        Optional<Leftbehind> Leftbehind = LeftbehindRepository.findById(leftId);
        ValidationUtil.isNull(Leftbehind,"Leftbehind","leftId",leftId);
        return LeftbehindMapper.toDto(Leftbehind.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeftbehindDTO create(Leftbehind resources) {
        resources.setLeftId(IdUtil.simpleUUID());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        if(LeftbehindRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(Leftbehind.class,"identity_num",resources.getIdentityNum());
        }
        return LeftbehindMapper.toDto(LeftbehindRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Leftbehind resources) {
        resources.setOperDate(new Timestamp(new Date().getTime()));
        Optional<Leftbehind> optionalLeftbehind = LeftbehindRepository.findById(resources.getLeftId());
        ValidationUtil.isNull( optionalLeftbehind,"Leftbehind","id",resources.getLeftId());
        Leftbehind Leftbehind = optionalLeftbehind.get();
        Leftbehind Leftbehind1 = null;
        Leftbehind1 = LeftbehindRepository.findByIdentityNum(resources.getIdentityNum());
        if(Leftbehind1 != null && !Leftbehind1.getLeftId().equals(Leftbehind.getLeftId())){
            throw new EntityExistException(Leftbehind.class,"identity_num",resources.getIdentityNum());
        }
        Leftbehind.copy(resources);
        LeftbehindRepository.save(Leftbehind);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String leftId) {
        LeftbehindRepository.deleteById(leftId);
    }
}