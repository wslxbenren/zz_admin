package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Keypersoninfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.KeypersoninfoRepository;
import com.xyz.modules.biz.service.KeypersoninfoService;
import com.xyz.modules.biz.service.dto.KeypersoninfoDTO;
import com.xyz.modules.biz.service.dto.KeypersoninfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.KeypersoninfoMapper;
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
public class KeypersoninfoServiceImpl implements KeypersoninfoService {

    @Autowired
    private KeypersoninfoRepository KeypersoninfoRepository;

    @Autowired
    private KeypersoninfoMapper KeypersoninfoMapper;

    @Override
    public Object queryAll(KeypersoninfoQueryCriteria criteria, Pageable pageable){
        Page<Keypersoninfo> page = KeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(KeypersoninfoMapper::toDto));
    }

    @Override
    public Object queryAll(KeypersoninfoQueryCriteria criteria){
        return KeypersoninfoMapper.toDto(KeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public KeypersoninfoDTO findById(String keyId) {
        Optional<Keypersoninfo> Keypersoninfo = KeypersoninfoRepository.findById(keyId);
        ValidationUtil.isNull(Keypersoninfo,"Keypersoninfo","keyId",keyId);
        return KeypersoninfoMapper.toDto(Keypersoninfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KeypersoninfoDTO create(Keypersoninfo resources) {
        resources.setKeyId(IdUtil.simpleUUID()); 
        return KeypersoninfoMapper.toDto(KeypersoninfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Keypersoninfo resources) {
        Optional<Keypersoninfo> optionalKeypersoninfo = KeypersoninfoRepository.findById(resources.getKeyId());
        ValidationUtil.isNull( optionalKeypersoninfo,"Keypersoninfo","id",resources.getKeyId());
        Keypersoninfo Keypersoninfo = optionalKeypersoninfo.get();
        Keypersoninfo.copy(resources);
        KeypersoninfoRepository.save(Keypersoninfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String keyId) {
        KeypersoninfoRepository.deleteById(keyId);
    }
}