package com.xyz.biz.http.security;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.secur.entity.*;
import com.xyz.modules.biz.service.secur.repo.*;
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

import static com.xyz.modules.system.util.DictEnum.XING_BIE;

/**
 * 描述: 治安管理
 *
 * @author 大豫竹
 * @create 2020-04-29 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockSecur extends MockBase {
    @Resource
    private BizSecurHomicidebaseinfoRepository bizSecurHomicidebaseinfoRepository;
    @Resource
    private BizSecurKeyareasRepository bizSecurKeyareasRepository;
    @Resource
    private BizSecurLogisticsRepository bizSecurLogisticsRepository;
    @Resource
    private SuspectinfoRepository suspectinfoRepository;
    @Resource
    private VictiminfoRepository victiminfoRepository;

    @Test
    public void init() {
//        BizSecurHomicidebaseinfoRepository();
        BizSecurKeyareasRepository();
        BizSecurLogisticsRepository();
//        SuspectinfoRepository();
//        VictiminfoRepository();
    }

    /**
     * 命案信息
     */
    @Test
    public void BizSecurHomicidebaseinfoRepository() {
        AtomicInteger step = new AtomicInteger(1);
        String[] a = new String[] {"盗窃致死", "杀人致死", "抢劫致死", "诈骗致死", "赌博致死", "吸毒致死", "嫖娼致死", "斗殴致死"};
        userList.parallelStream().forEach(i -> {
            BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo = new BizSecurHomicidebaseinfo();
            bizSecurHomicidebaseinfo.setCaseId(UUID.randomUUID().toString());
            bizSecurHomicidebaseinfo.setCaseName(a[RandomUtils.nextInt(0, 8)] + step.addAndGet(1) + "案件");
            bizSecurHomicidebaseinfo.setCaseCode(getRandomCode(8));
            bizSecurHomicidebaseinfo.setCrimeDate(getTime());
            bizSecurHomicidebaseinfo.setEndDate(getTime());
            bizSecurHomicidebaseinfo.setBriefdescrip(mockRandomZh_cn());
            bizSecurHomicidebaseinfo.setEffDate(getTime());
            bizSecurHomicidebaseinfo.setExpDate(getTime());
            bizSecurHomicidebaseinfo.setStatus("1");
            bizSecurHomicidebaseinfo.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            bizSecurHomicidebaseinfo.setOperName(i.getId());
            bizSecurHomicidebaseinfo.setOperDate(getTime());
            bizSecurHomicidebaseinfo.setCreator(i.getId());
            bizSecurHomicidebaseinfo.setCreateTime(getTime());
            bizSecurHomicidebaseinfo.setUnitCode(i.getDept().getCode());
            bizSecurHomicidebaseinfoRepository.save(bizSecurHomicidebaseinfo);
        });

    }
    @Test
    public void BizSecurKeyareasRepository() {
        String[] problem = new String[] {"校园欺凌", "宅基地争议", "土地争议", "劳务纠纷"};
        userList.parallelStream().forEach(i -> {
            BizSecurKeyareas bizSecurKeyareas = new BizSecurKeyareas();
            bizSecurKeyareas.setKeyId(UUID.randomUUID().toString());
            bizSecurKeyareas.setSecurityKarea(getRandomAddrDetail());
            bizSecurKeyareas.setOutproblem(getRandomDictValue(DictEnum.ZATCWT.getDictId()));
            bizSecurKeyareas.setAreaType(getRandomDictValue(DictEnum.SJQYLX.getDictId()));
            bizSecurKeyareas.setAreaScope("范围");
            bizSecurKeyareas.setLeadunitCode(i.getDept().getCode());
            bizSecurKeyareas.setPartunitCode(i.getDept().getCode());
            bizSecurKeyareas.setLeadunitPername(mockNameCn(2));
            bizSecurKeyareas.setLeadunitPermobile(mockPhone());
            bizSecurKeyareas.setLimitTime(getTime());
            bizSecurKeyareas.setCriminalcaseNum(RandomUtils.nextInt(0, 10) + "件");
            bizSecurKeyareas.setSecuritycaseNum(RandomUtils.nextInt(0, 10) + "件");
            bizSecurKeyareas.setRegulation(mockRandomZh_cn());
            bizSecurKeyareas.setEvaluation(getRandomDictValue(DictEnum.XGPG.getDictId()));
            bizSecurKeyareas.setEffDate(getTime());
            bizSecurKeyareas.setExpDate(getTime());
            bizSecurKeyareas.setStatus("1");
            bizSecurKeyareas.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            bizSecurKeyareas.setOperName(i.getId());
            bizSecurKeyareas.setOperDate(getTime());
            bizSecurKeyareas.setCreator(i.getId());
            bizSecurKeyareas.setCreateTime(getTime());
            bizSecurKeyareas.setUnitCode(i.getDept().getCode());
            bizSecurKeyareasRepository.save(bizSecurKeyareas);
        });

    }
    @Test
    public void BizSecurLogisticsRepository() {
        userList.parallelStream().forEach(i -> {
            BizSecurLogistics bizSecurLogistics = new BizSecurLogistics();
            bizSecurLogistics.setLogisId(UUID.randomUUID().toString());
            bizSecurLogistics.setCreditCode(getRandomCode(18));
            bizSecurLogistics.setLogientityName(mockRandomZh_cn());
            bizSecurLogistics.setEntityaddrCode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            bizSecurLogistics.setEntityaddr(getRandomAddrDetail());
            bizSecurLogistics.setEntityPhone(mockPhone());
            bizSecurLogistics.setEntityleadName(mockNameCn(3));
            bizSecurLogistics.setEntityleadMobile(mockPhone());
            bizSecurLogistics.setRegisType(getRandomDictValue(DictEnum.DJZCLX.getDictId()));
            bizSecurLogistics.setHoldings(getRandomDictValue(DictEnum.KGQK.getDictId()));
            bizSecurLogistics.setBusinessScope(getRandomDictValue(DictEnum.JYFW.getDictId()));
            bizSecurLogistics.setEntityType(getRandomDictValue(DictEnum.QYLX.getDictId()));
            bizSecurLogistics.setServiceBrand(mockRandomZh_cn());
            bizSecurLogistics.setPractiNum(RandomUtils.nextInt(0, 100));
            bizSecurLogistics.setCameraNum(RandomUtils.nextInt(0, 100));
            bizSecurLogistics.setXrayNum(RandomUtils.nextInt(0, 100));
            bizSecurLogistics.setIfPriori(RandomUtils.nextInt(0, 2));
            bizSecurLogistics.setIfRealname(RandomUtils.nextInt(0, 2));
            bizSecurLogistics.setIfSecucheck(RandomUtils.nextInt(0, 2));
            bizSecurLogistics.setLng(RandomUtils.nextDouble(30, 50));
            bizSecurLogistics.setLat(RandomUtils.nextDouble(30, 50));
            bizSecurLogistics.setEffDate(getTime());
            bizSecurLogistics.setExpDate(getTime());
            bizSecurLogistics.setStatus("1");
            bizSecurLogistics.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            bizSecurLogistics.setOperName(i.getId());
            bizSecurLogistics.setOperDate(getTime());
            bizSecurLogistics.setCreator(i.getId());
            bizSecurLogistics.setCreateTime(getTime());
            bizSecurLogistics.setUnitCode(i.getDept().getCode());
            bizSecurLogisticsRepository.save(bizSecurLogistics);
        });

    }
    @Test
    public void SuspectinfoRepository() {
        AtomicInteger step = new AtomicInteger(1000);
        List<BizSecurHomicidebaseinfo> res = bizSecurHomicidebaseinfoRepository.findAll();
        res.parallelStream().forEach(i -> {
            Suspectinfo suspectinfo = new Suspectinfo();
            suspectinfo.setSuspId(UUID.randomUUID().toString());
            suspectinfo.setSuspectCode(step.addAndGet(1));
            suspectinfo.setCaseCode(i.getCaseCode());
            suspectinfo.setCardType(getRandomDictValue(DictEnum.ZJDM.getDictId()));
            suspectinfo.setCardCode(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            suspectinfo.setPersonName(mockNameCn(2));
            suspectinfo.setUsedName(mockNameCn(2));
            suspectinfo.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            suspectinfo.setDateBirth(getTime());
            suspectinfo.setCountry(getRandomDictValue(DictEnum.GJ_DQ.getDictId()));
            suspectinfo.setNation(getRandomDictValue(DictEnum.MIN_ZU.getDictId()));
            suspectinfo.setNativeInfo(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            suspectinfo.setNativeAddr(getRandomAddrDetail());
            suspectinfo.setMarriageFlag(getRandomDictValue(DictEnum.HYZK.getDictId()));
            suspectinfo.setPartyFlag(getRandomDictValue(DictEnum.ZZMM.getDictId()));
            suspectinfo.setEducationBg(getRandomDictValue(DictEnum.XUE_LI.getDictId()));
            suspectinfo.setFaithType(getRandomDictValue(DictEnum.ZJXY.getDictId()));
            suspectinfo.setVocationCode(getRandomDictValue(DictEnum.ZYLB.getDictId()));
            suspectinfo.setVocation(mockRandomZh_cn() + "职业");
            suspectinfo.setServiceAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            suspectinfo.setServiceAddr(getRandomAddrDetail());
            suspectinfo.setRegisteredPlace(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            suspectinfo.setRegisteredAddr(getRandomAddrDetail());
            suspectinfo.setResidence(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            suspectinfo.setResidenceAddr(getRandomAddrDetail());
            suspectinfo.setIfPsychopath(RandomUtils.nextInt(0, 2));
            suspectinfo.setIfMinors(RandomUtils.nextInt(0, 2));
            suspectinfo.setIfTeenagers(RandomUtils.nextInt(0, 2));
            suspectinfo.setEffDate(getTime());
            suspectinfo.setExpDate(getTime());
            suspectinfo.setStatus("1");
            suspectinfo.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            suspectinfo.setOperName(i.getCreator());
            suspectinfo.setOperDate(getTime());
            suspectinfo.setCreator(i.getCreator());
            suspectinfo.setCreateTime(getTime());
            suspectinfo.setUnitCode(i.getUnitCode());
            suspectinfoRepository.save(suspectinfo);
        });

    }
    @Test
    public void VictiminfoRepository() {
        List<BizSecurHomicidebaseinfo> res = bizSecurHomicidebaseinfoRepository.findAll();
        res.parallelStream().forEach(i -> {
            Victiminfo victiminfo = new Victiminfo();
            victiminfo.setVicId(UUID.randomUUID().toString());
            victiminfo.setVictimCode(getRandomCode(8));
            victiminfo.setCaseCode(i.getCaseCode());
            victiminfo.setCardType(getRandomDictValue(DictEnum.ZJDM.getDictId()));
            victiminfo.setCardCode(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            victiminfo.setPersonName(mockNameCn(2));
            victiminfo.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            victiminfo.setDateBirth(getTime());
            victiminfo.setCountry(getRandomDictValue(DictEnum.GJ_DQ.getDictId()));
            victiminfo.setNation(getRandomDictValue(DictEnum.MIN_ZU.getDictId()));
            victiminfo.setNativeInfo(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            victiminfo.setNativeAddr(getRandomAddrDetail());
            victiminfo.setMarriageFlag(getRandomDictValue(DictEnum.HYZK.getDictId()));
            victiminfo.setPartyFlag(getRandomDictValue(DictEnum.ZZMM.getDictId()));
            victiminfo.setEducationBg(getRandomDictValue(DictEnum.XUE_LI.getDictId()));
            victiminfo.setFaithType(getRandomDictValue(DictEnum.ZJXY.getDictId()));
            victiminfo.setVocationCode(getRandomDictValue(DictEnum.ZYLB.getDictId()));
            victiminfo.setVocation(mockRandomZh_cn() + "职业");
            victiminfo.setServiceAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            victiminfo.setServiceAddr(getRandomAddrDetail());
            victiminfo.setRegisteredPlace(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            victiminfo.setRegisteredAddr(getRandomAddrDetail());
            victiminfo.setResidence(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            victiminfo.setResidenceAddr(getRandomAddrDetail());
            victiminfo.setEffDate(getTime());
            victiminfo.setExpDate(getTime());
            victiminfo.setStatus("1");
            victiminfo.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            victiminfo.setOperName(i.getCreator());
            victiminfo.setOperDate(getTime());
            victiminfo.setCreator(i.getCreator());
            victiminfo.setCreateTime(getTime());
            victiminfo.setUnitCode(i.getUnitCode());
            victiminfoRepository.save(victiminfo);
        });
    }
}