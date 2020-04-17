package com.xyz.biz.http.special;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.special.entity.*;
import com.xyz.modules.biz.service.special.repo.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

import static com.xyz.modules.system.util.DictEnum.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockSpecial extends MockBase {
    @Resource
    private AidsPersonRepository aidsPersonReposity;

    @Resource
    private BegPersonRepository begPersonReposity;

    @Resource
    private CorrectPersonRepository correctPersonReposity;

    @Resource
    private DrugPersonRepository drugPersonReposity;

    @Resource
    private PsychosisPersonRepository psychosisPersonReposity;

    @Resource
    private ReleasedPersonRepository releasedPersonReposity;

    @Test
    public void genAidsPerson() {
        AidsPerson aidsPerson = new AidsPerson();
        userList.forEach(i -> {
            aidsPerson.setAidsId(UUID.randomUUID().toString());
            aidsPerson.setPersonName(mockNameCn(3));
            aidsPerson.setUsedName(mockNameCn(3));
            aidsPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            aidsPerson.setDateBirth(getTime());
            aidsPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            aidsPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()));
            aidsPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            aidsPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            aidsPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            aidsPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            aidsPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            aidsPerson.setServicePlace(getRandomCode(4));
            aidsPerson.setContact(mockPhone());
            aidsPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setRegisteredAddress(getRandomAddrDetail());
            aidsPerson.setRoutesInfection(getRandomDictValue(GRTJ.getDictId()));
//            aidsPerson.setIsPedigree(); 是否感染
            aidsPerson.setPedigreeComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "犯罪说明");
            aidsPerson.setCaseType(getRandomDictValue(AJLX.getDictId()));
            aidsPerson.setTakeType(getRandomDictValue(GZLX.getDictId()));
            aidsPerson.setHelpComments(RandomStringUtils.random(10, 0x4e00, 0x9fa5, false,false)+ " 帮扶");
            aidsPerson.setHelperName(mockNameCn(3));
            aidsPerson.setHelperAddress(getRandomAddrDetail());
            aidsPerson.setDetainType(getRandomDictValue(SZQK.getDictId()));
            aidsPerson.setDetainUnion(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false)+ " 机构");
            aidsPerson.setEffDate(getTime());
            aidsPerson.setExpDate(getTime());
            aidsPerson.setStatus("1");
            aidsPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            aidsPerson.setOperName(i.getId());
            aidsPerson.setOperDate(getTime());
            aidsPerson.setCreator(i.getId());
            aidsPerson.setCreateTime(getTime());
            aidsPerson.setUnitCode(i.getDept().getCode());
            aidsPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            aidsPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setResidenceAddr(getRandomAddrDetail());
            aidsPerson.setNativeInfoAddr(getRandomAddrDetail());
            aidsPerson.setServicePlaceCode(getRandomCode(4));
            aidsPersonReposity.save(aidsPerson);
        });
    }

    @Test
    public void genBegPerson() {
        BegPerson begPerson = new BegPerson();
        userList.forEach(i -> {
            begPersonReposity.save(begPerson);
        });
    }

    @Test
    public void genCorrectPerson() {
        CorrectPerson correctPerson = new CorrectPerson();
        userList.forEach(i -> {
            correctPersonReposity.save(correctPerson);
        });
    }

    @Test
    public void genDrugPerson() {
        DrugPerson drugPerson = new DrugPerson();
        userList.forEach(i -> {
            drugPersonReposity.save(drugPerson);
        });
    }

    @Test
    public void genPsychosisPerson() {
        PsychosisPerson psychosisPerson = new PsychosisPerson();
        userList.forEach(i -> {
            psychosisPersonReposity.save(psychosisPerson);
        });
    }

    @Test
    public void genReleasedPerson() {
        ReleasedPerson releasedPerson = new ReleasedPerson();
        userList.forEach(i -> {
            releasedPersonReposity.save(releasedPerson);
        });
    }


}
