package com.xyz.biz.http.route;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.route.entity.Caseinfo;
import com.xyz.modules.biz.service.route.entity.Convenientinfo;
import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.modules.biz.service.route.repo.CaseinfoRepository;
import com.xyz.modules.biz.service.route.repo.ConvenientinfoRepository;
import com.xyz.modules.biz.service.route.repo.KeypersoninfoRepository;
import com.xyz.modules.biz.service.route.repo.RectificmanageRepository;
import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.util.DictEnum;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.xyz.modules.system.util.DictEnum.*;

/**
 * 描述:
 *
 * @author 大豫竹
 * @create 2020-04-29 18:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockRoute extends MockBase {
    @Resource
    private CaseinfoRepository caseinfoRepository;
    @Resource
    private ConvenientinfoRepository convenientinfoRepository;
    @Resource
    private KeypersoninfoRepository keypersoninfoRepository;
    @Resource
    private RectificmanageRepository rectificmanageRepository;

    @Test
    public void init() {
        caseinfo();
        keypersoninfo();
        rectificmanage();
    }

    public void caseinfo() {
        List<Convenientinfo> list = convenientinfoRepository.findAll();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        list.parallelStream().forEach(i -> {
            Caseinfo caseinfo = new Caseinfo();
            caseinfo.setCaseId(UUID.randomUUID().toString());
            caseinfo.setConId(i.getConId());
            caseinfo.setRouteName("某线路" + atomicInteger.addAndGet(1));
            caseinfo.setCaseCode(getRandomCode(4));
            caseinfo.setCaseName("案件" + atomicInteger.get());
            caseinfo.setCaseType(getRandomDictValue(DictEnum.AJLX.getDictId()));
            caseinfo.setHappenTime(getTime());
            caseinfo.setHappenAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            caseinfo.setHappenAddr(getRandomAddrDetail());
            caseinfo.setCaseNature(getRandomDictValue(DictEnum.AJXZ.getDictId()));
            caseinfo.setCaseInfo(mockRandomZh_cn());
            caseinfo.setPrinccardType(getRandomDictValue(DictEnum.ZJDM.getDictId()));
            caseinfo.setPrinccardCode(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            caseinfo.setPrincipalName(mockNameCn(2));
            caseinfo.setIfSolve(RandomUtils.nextInt(0, 2));
            caseinfo.setCrimeNum(RandomUtils.nextInt(0, 100));
            caseinfo.setOnrunNum(RandomUtils.nextInt(0, 100));
            caseinfo.setCaptureNum(RandomUtils.nextInt(0, 100));
            caseinfo.setSolveInfo(mockRandomZh_cn());
            caseinfo.setEffDate(getTime());
            caseinfo.setExpDate(getTime());
            caseinfo.setStatus("1");
            caseinfo.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            caseinfo.setOperName(i.getOperName());
            caseinfo.setOperDate(getTime());
            caseinfo.setCreator(i.getCreator());
            caseinfo.setCreateTime(getTime());
            caseinfo.setUnitCode(i.getUnitCode());
            caseinfoRepository.save(caseinfo);
        });
    }

    /**
     * 线路基本表
     */
    @Test
    public void convenientinfo() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        userList.parallelStream().forEach(i -> {
            Convenientinfo convenientinfo = new Convenientinfo();
            convenientinfo.setConId(UUID.randomUUID().toString());
            convenientinfo.setRouteName("线路" + atomicInteger.addAndGet(1));
            convenientinfo.setRouteCode(String.valueOf(atomicInteger.get()));
            convenientinfo.setRouteType(getRandomDictValue(DictEnum.LXLX.getDictId()));
            convenientinfo.setSubordunit(i.getDept().getCode());
            convenientinfo.setSubordunitAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            convenientinfo.setSubordunitAddr(getRandomAddrDetail());
            convenientinfo.setSubordunitPhone(mockPhone());
            convenientinfo.setSubordName(mockNameCn(2));
            convenientinfo.setSubordMobile(mockPhone());
            convenientinfo.setJurisdunit(atomicInteger.get() + "单位");
            convenientinfo.setJurisdunitAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            convenientinfo.setJurisdunitAddr(getRandomAddrDetail());
            convenientinfo.setJurisdunitPhone(mockPhone());
            convenientinfo.setChargeName(mockNameCn(2));
            convenientinfo.setChargeMobile(mockPhone());
            convenientinfo.setSecuhiddenInfo("治安隐患" + atomicInteger.get());
            convenientinfo.setSecuhiddenLevel(getRandomDictValue(DictEnum.ZAYHDJ.getDictId()));
            convenientinfo.setEffDate(getTime());
            convenientinfo.setExpDate(getTime());
            convenientinfo.setStatus("1");
            convenientinfo.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            convenientinfo.setOperName(i.getId());
            convenientinfo.setOperDate(getTime());
            convenientinfo.setCreator(i.getId());
            convenientinfo.setCreateTime(getTime());
            convenientinfo.setUnitCode(i.getDept().getCode());
            convenientinfoRepository.save(convenientinfo);
        });
    }

    public void keypersoninfo() {
        List<Convenientinfo> list = convenientinfoRepository.findAll();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        list.parallelStream().forEach(i -> {
            Keypersoninfo keypersoninfo = new Keypersoninfo();
            keypersoninfo.setKeyId(UUID.randomUUID().toString());
            keypersoninfo.setRouteName("某线路" + atomicInteger.addAndGet(1));
            keypersoninfo.setKeypersonType(getRandomDictValue(DictEnum.RYLX.getDictId()));
            keypersoninfo.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            keypersoninfo.setPersonName(mockNameCn(2));
            keypersoninfo.setUsedName(mockNameCn(2));
            keypersoninfo.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            keypersoninfo.setDateBirth(getTime());
            keypersoninfo.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            keypersoninfo.setNativeInfo(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            keypersoninfo.setNativeAddr(getRandomAddrDetail());
            keypersoninfo.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            keypersoninfo.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            keypersoninfo.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            keypersoninfo.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            keypersoninfo.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            keypersoninfo.setVocation(mockRandomZh_cn());
            keypersoninfo.setServiceAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            keypersoninfo.setServiceAddr(getRandomAddrDetail());
            keypersoninfo.setMobile(mockPhone());
            keypersoninfo.setRegisteredPlace(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            keypersoninfo.setRegisteredAddr(getRandomAddrDetail());
            keypersoninfo.setResidence(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            keypersoninfo.setResidenceAddr(getRandomAddrDetail());
            keypersoninfo.setIfFocus(RandomUtils.nextInt(0, 2));
            keypersoninfo.setHazardLevel(getRandomDictValue(WXCD.getDictId()));
            keypersoninfo.setHazardInfo(mockRandomZh_cn());
            keypersoninfo.setEffDate(getTime());
            keypersoninfo.setExpDate(getTime());
            keypersoninfo.setStatus("1");
            keypersoninfo.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            keypersoninfo.setOperName(i.getOperName());
            keypersoninfo.setOperDate(getTime());
            keypersoninfo.setCreator(i.getCreator());
            keypersoninfo.setCreateTime(getTime());
            keypersoninfo.setUnitCode(i.getUnitCode());
            keypersoninfoRepository.save(keypersoninfo);
        });
    }

    public void rectificmanage() {
        List<Convenientinfo> list = convenientinfoRepository.findAll();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        list.parallelStream().forEach(i -> {
            Rectificmanage rectificmanage = new Rectificmanage();
            rectificmanage.setRectId(UUID.randomUUID().toString());
            rectificmanage.setConId(i.getConId());
            rectificmanage.setRouteName("某线路" + atomicInteger.addAndGet(1));
            rectificmanage.setActionType(mockRandomZh_cn());
            rectificmanage.setActionTheme(mockRandomZh_cn());
            rectificmanage.setActionAddr(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            rectificmanage.setActionTime(getTime());
            rectificmanage.setOutNum(RandomUtils.nextInt(0, 100));
            rectificmanage.setWorkIndic(mockRandomZh_cn());
            rectificmanage.setWorkInfo(mockRandomZh_cn());
            rectificmanage.setResponName(mockNameCn(2));
            rectificmanage.setResponUnit(i.getUnitCode());
            rectificmanage.setAssistUnit(i.getUnitCode());
            rectificmanage.setAssistOutnum(RandomUtils.nextInt(0, 100));
            rectificmanage.setReguresults(mockRandomZh_cn());
            rectificmanage.setEffDate(getTime());
            rectificmanage.setExpDate(getTime());
            rectificmanage.setStatus("1");
            rectificmanage.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            rectificmanage.setOperName(i.getOperName());
            rectificmanage.setOperDate(getTime());
            rectificmanage.setCreator(i.getCreator());
            rectificmanage.setCreateTime(getTime());
            rectificmanage.setUnitCode(i.getUnitCode());
            rectificmanageRepository.save(rectificmanage);
        });
    }
}