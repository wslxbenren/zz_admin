package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Victiminfo;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.VictiminfoRepository;
import com.xyz.modules.biz.service.VictiminfoService;
import com.xyz.modules.biz.service.dto.VictiminfoDTO;
import com.xyz.modules.biz.service.dto.VictiminfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.VictiminfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VictiminfoServiceImpl implements VictiminfoService {

    @Autowired
    private VictiminfoRepository VictiminfoRepository;

    @Autowired
    private VictiminfoMapper VictiminfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    @Transactional
    public Object queryAll(VictiminfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/命案受害人信息--开始");
        Page<Victiminfo> page = VictiminfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(VictiminfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(VictiminfoQueryCriteria criteria){
        return VictiminfoMapper.toDto(VictiminfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public VictiminfoDTO findById(String vicId) {
        log.info("详情社会治安管理/命案受害人信息--开始");
        if (StringUtils.isBlank(vicId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Victiminfo> Victiminfo = VictiminfoRepository.findById(vicId);
        ValidationUtil.isNull(Victiminfo,"Victiminfo","vicId",vicId);
        return VictiminfoMapper.toDto(Victiminfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VictiminfoDTO create(Victiminfo resources) {
        log.info("新增社会治安管理/命案受害人信息--开始");
        resources.setVicId(IdUtil.simpleUUID()); 
        return VictiminfoMapper.toDto(VictiminfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Victiminfo resources) {
        log.info("修改社会治安管理/命案受害人信息--开始");
        if (StringUtils.isBlank(resources.getVicId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Victiminfo> optionalVictiminfo = VictiminfoRepository.findById(resources.getVicId());
        ValidationUtil.isNull( optionalVictiminfo,"Victiminfo","id",resources.getVicId());
        Victiminfo Victiminfo = optionalVictiminfo.get();
        Victiminfo.copy(resources);
        VictiminfoRepository.save(Victiminfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String vicId) {
        log.info("删除社会治安管理/命案受害人信息--开始");
        VictiminfoRepository.deleteById(vicId);
    }
}