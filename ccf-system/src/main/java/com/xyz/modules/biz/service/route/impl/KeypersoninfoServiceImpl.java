package com.xyz.modules.biz.service.route.impl;

import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.route.repo.KeypersoninfoRepository;
import com.xyz.modules.biz.service.route.KeypersoninfoService;
import com.xyz.modules.biz.service.route.dto.KeypersoninfoDTO;
import com.xyz.modules.biz.service.route.qo.KeypersoninfoQueryCriteria;
import com.xyz.modules.biz.service.route.mapper.KeypersoninfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(KeypersoninfoQueryCriteria criteria, Pageable pageable){
//        Page<Keypersoninfo> page = KeypersoninfoRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
//                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);


        Page<Keypersoninfo> page = KeypersoninfoRepository.findAll(audit.genSpecification(criteria), pageable);
        List<KeypersoninfoDTO> KeypersoninfoDTOList = KeypersoninfoMapper.toDto(page.getContent());
        for (KeypersoninfoDTO dto:KeypersoninfoDTOList  )
        {
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), dto.getPersonSex());
            dto.setPersonSex(dd == null ? "无数据" : dd);//性别

            dd=dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(),dto.getNation());
            dto.setNationStr(dd == null ? "无数据" : dd);//民族

            dd=dictDetailService.transDict(DictEnum.ADDRESS.getDistName(),dto.getNativeInfo());
            dto.setNativeInfoStr(dd == null ? "无数据" : dd);//籍贯

            dd=dictDetailService.transDict(DictEnum.HYZK.getDistName(),dto.getMarriageFlag());
            dto.setMarriageFlagStr(dd == null ? "无数据" : dd);//婚姻状况

            dd=dictDetailService.transDict(DictEnum.ZZMM.getDistName(),dto.getPartyFlag());
            dto.setPartyFlagStr(dd == null ? "无数据" : dd);//政治面貌

            dd=dictDetailService.transDict(DictEnum.XUE_LI.getDistName(),dto.getEducationBg());
            dto.setEducationBgStr(dd == null ? "无数据" : dd);//学历

            dd=dictDetailService.transDict(DictEnum.ZJXY.getDistName(),dto.getFaithType());
            dto.setPartyFlagStr(dd == null ? "无数据" : dd);//宗教信仰

            dd=dictDetailService.transDict(DictEnum.ZYLB.getDistName(),dto.getVocationCode());
            dto.setVocationCodeStr(dd == null ? "无数据" : dd);//职业类型


            dd=dictDetailService.transDict(DictEnum.ADDRESS.getDistName(),dto.getRegisteredPlace());
            dto.setRegisteredPlaceStr(dd == null ? "无数据" : dd);//户籍地


            dd=dictDetailService.transDict(DictEnum.ADDRESS.getDistName(),dto.getResidence());
            dto.setResidenceStr(dd == null ? "无数据" : dd);//现住地


            dd=dictDetailService.transDict(DictEnum.WXCD.getDistName(),dto.getHazardLevel());
            dto.setHazardLevelStr(dd == null ? "无数据" : dd);//危害程度

            dd=deptRepository.findNameByCode(dto.getUnitCode());
            dto.setUnitCodeStr(dd);//所属单位
        }


        return PageUtil.toPage(page.map(KeypersoninfoMapper::toDto));
    }

    @Override
    @Transactional
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