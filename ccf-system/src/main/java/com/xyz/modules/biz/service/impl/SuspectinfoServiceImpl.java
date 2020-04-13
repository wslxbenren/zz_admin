package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Suspectinfo;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.SuspectinfoRepository;
import com.xyz.modules.biz.service.SuspectinfoService;
import com.xyz.modules.biz.service.dto.SuspectinfoDTO;
import com.xyz.modules.biz.service.dto.SuspectinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.SuspectinfoMapper;
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

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuspectinfoServiceImpl implements SuspectinfoService {

    @Autowired
    private SuspectinfoRepository SuspectinfoRepository;

    @Autowired
    private SuspectinfoMapper SuspectinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/命案犯罪嫌疑人信息--开始");
        Page<Suspectinfo> page = SuspectinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(SuspectinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria){
        return SuspectinfoMapper.toDto(SuspectinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public SuspectinfoDTO findById(String suspId) {
        log.info("详情社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> Suspectinfo = SuspectinfoRepository.findById(suspId);
        ValidationUtil.isNull(Suspectinfo,"Suspectinfo","suspId",suspId);
        return SuspectinfoMapper.toDto(Suspectinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectinfoDTO create(Suspectinfo resources) {
        log.info("新增社会治安管理/命案犯罪嫌疑人信息--开始");
        resources.setSuspId(IdUtil.simpleUUID());
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return SuspectinfoMapper.toDto(SuspectinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Suspectinfo resources) {
        log.info("修改社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(resources.getSuspId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Suspectinfo> optionalSuspectinfo = SuspectinfoRepository.findById(resources.getSuspId());
        ValidationUtil.isNull( optionalSuspectinfo,"Suspectinfo","id",resources.getSuspId());
        Suspectinfo Suspectinfo = optionalSuspectinfo.get();
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setOperName(u.getId());
        Suspectinfo.copy(resources);
        SuspectinfoRepository.save(Suspectinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String suspId) {
        log.info("删除社会治安管理/命案犯罪嫌疑人信息--开始");
        if (StringUtils.isBlank(suspId)){
            throw new BadRequestException("主键ID不能为空");
        }
        SuspectinfoRepository.deleteById(suspId);
    }
}