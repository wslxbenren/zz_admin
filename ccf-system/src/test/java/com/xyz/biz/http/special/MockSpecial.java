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
    public void init() {
        genAidsPerson();
        genBegPerson();
        genCorrectPerson();
        genDrugPerson();
        genPsychosisPerson();
        genReleasedPerson();
    }

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
            aidsPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            aidsPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            aidsPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            aidsPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            aidsPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            aidsPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            aidsPerson.setServicePlace(getRandomAddrDetail());
            aidsPerson.setContact(mockPhone());
            aidsPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setRegisteredAddress(getRandomAddrDetail());
            aidsPerson.setRoutesInfection(getRandomDictValue(GRTJ.getDictId()));
            aidsPerson.setIsPedigree(String.valueOf(RandomUtils.nextInt(0, 2)));
            aidsPerson.setPedigreeComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "犯罪说明");
            aidsPerson.setCaseType(getRandomDictValue(AJLX.getDictId()));
            aidsPerson.setTakeType(getRandomDictValue(GZLX.getDictId()));
            aidsPerson.setHelpComments(getRandomDictValue(BFQK.getDictId()));
            aidsPerson.setHelperName(mockNameCn(3));
            aidsPerson.setHelperAddress(getRandomAddrDetail());
            aidsPerson.setDetainType(getRandomDictValue(SZQK.getDictId()));
            aidsPerson.setDetainUnion(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false)+ " 机构");
            aidsPerson.setEffDate(getTime());
            aidsPerson.setExpDate(getTime());
            aidsPerson.setStatus("1");
            aidsPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            aidsPerson.setOperName(mockNameCn(2));
            aidsPerson.setOperDate(getTime());
            aidsPerson.setCreator(i.getId());
            aidsPerson.setCreateTime(getTime());
            aidsPerson.setUnitCode(i.getDept().getCode());
            aidsPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            aidsPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            aidsPerson.setResidenceAddr(getRandomAddrDetail());
            aidsPerson.setNativeInfoAddr(getRandomAddrDetail());
            aidsPerson.setServicePlaceCode(getRandomDictValue(ADDRESS.getDictId()));
            aidsPersonReposity.save(aidsPerson);
        });
    }

    @Test
    public void genBegPerson() {
        BegPerson begPerson = new BegPerson();
        userList.forEach(i -> {
            begPerson.setBegId(UUID.randomUUID().toString());
            begPerson.setPersonName(mockNameCn(3));
            begPerson.setUsedName(mockNameCn(3));
            begPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            begPerson.setDateBirth(getTime());
            begPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            begPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            begPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            begPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            begPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            begPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            begPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            begPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setServicePlace(getRandomAddrDetail());
            begPerson.setContact(mockPhone());
            begPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            begPerson.setRegisteredAddress(getRandomAddrDetail());
            begPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            begPerson.setResidenceAddress(getRandomAddrDetail());
            begPerson.setBelongType("none");
            begPerson.setLeftoverChild(String.valueOf(RandomUtils.nextInt(0, 2)));
            begPerson.setStudyComment("unkonw");
            begPerson.setUnstudyReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setIsEnsure(String.valueOf(RandomUtils.nextInt(0, 2)));
            begPerson.setStopstudyReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setStopstudyOther(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setIsPedigree(String.valueOf(RandomUtils.nextInt(0, 2)));
            begPerson.setWorkComment(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setWorkOther(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setBadBehaviour(String.valueOf(RandomUtils.nextInt(0, 2)));
            begPerson.setBehaviourOther(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setUnworkReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setUnworkOther(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setGuardFlag(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setGuardReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setOtherComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setSourceIncome(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setSourceOther(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            begPerson.setEffDate(getTime());
            begPerson.setExpDate(getTime());
            begPerson.setStatus("1");
            begPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            begPerson.setOperName(i.getId());
            begPerson.setOperDate(getTime());
            begPerson.setCreator(i.getId());
            begPerson.setCreateTime(getTime());
            begPerson.setUnitCode(i.getDept().getCode());
            begPersonReposity.save(begPerson);
        });
    }

    @Test
    public void genCorrectPerson() {
        CorrectPerson correctPerson = new CorrectPerson();
        userList.forEach(i -> {
            correctPerson.setCorrectId(UUID.randomUUID().toString());
            correctPerson.setPersonName(mockNameCn(3));
            correctPerson.setUsedName(mockNameCn(3));
            correctPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            correctPerson.setDateBirth(getTime());
            correctPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            correctPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            correctPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            correctPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            correctPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            correctPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            correctPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            correctPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setServicePlace(getRandomCode(4));
            correctPerson.setContact(mockPhone());
            correctPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            correctPerson.setRegisteredAddress(getRandomAddrDetail());
            correctPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            correctPerson.setResidenceCode(getRandomDictValue(ADDRESS.getDictId()));
            correctPerson.setResidenceAddress(getRandomAddrDetail());
            correctPerson.setCorrectCode(getRandomCode(4));
            correctPerson.setDetainUnion(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setCorrectType(getRandomDictValue(JZLX.getDictId()));
            correctPerson.setCaseType(getRandomDictValue(AJLX.getDictId()));
            correctPerson.setChargeComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setPrisonTerm(RandomUtils.nextInt(1, 10) + "年");
            correctPerson.setPrisonBeagindate(getTime());
            correctPerson.setPrisonEnddate(getTime());
            correctPerson.setCorrectBeagindate(getTime());
            correctPerson.setCorrectEnddate(getTime());
            correctPerson.setReviceFlag(getRandomDictValue(JSFS.getDictId()));
            correctPerson.setSishiFlag(getRandomDictValue(SSHIQK.getDictId()));
            correctPerson.setIsRecidivist(String.valueOf(RandomUtils.nextInt(0, 2)));
            correctPerson.setSansheFlag(getRandomDictValue(SSHEQK.getDictId()));
            correctPerson.setIsTeam(String.valueOf(RandomUtils.nextInt(0, 2)));
            correctPerson.setTeamGuys(mockNameCn(3) + "/" + mockNameCn(3));
            correctPerson.setCorrectRemove("");
            correctPerson.setIsBreakmanage(String.valueOf(RandomUtils.nextInt(0, 2)));
            correctPerson.setBreakmanageReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setCheckComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setBreakmanageCorrect(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setIsOmit(String.valueOf(RandomUtils.nextInt(0, 2)));
            correctPerson.setOmitReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setCheckOmit(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setOmitCorrect(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setBonusPenalty(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setPrisonChange(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setIsAgain(String.valueOf(RandomUtils.nextInt(0, 2)));
            correctPerson.setAgainCharge(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            correctPerson.setEffDate(getTime());
            correctPerson.setExpDate(getTime());
            correctPerson.setStatus("1");
            correctPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            correctPerson.setOperName(i.getId());
            correctPerson.setOperDate(getTime());
            correctPerson.setCreator(i.getId());
            correctPerson.setCreateTime(getTime());
            correctPerson.setUnitCode(i.getDept().getCode());
            correctPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            correctPerson.setNativeInfoAddr(getRandomAddrDetail());
            correctPerson.setServicePlaceCode(getRandomDictValue(ADDRESS.getDictId()));
            correctPersonReposity.save(correctPerson);
        });
    }

    @Test
    public void genDrugPerson() {
        DrugPerson drugPerson = new DrugPerson();

        userList.forEach(i -> {
            drugPerson.setDrugId(UUID.randomUUID().toString());
            drugPerson.setPersonName(mockNameCn(3));
            drugPerson.setUsedName(mockNameCn(3));
            drugPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            drugPerson.setDateBirth(getTime());
            drugPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            drugPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            drugPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            drugPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            drugPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            drugPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            drugPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            drugPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            drugPerson.setServicePlace(getRandomAddrDetail());
            drugPerson.setContact(mockPhone());
            drugPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            drugPerson.setRegisteredAddress(getRandomAddrDetail());
            drugPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            drugPerson.setResidenceCode(getRandomDictValue(ADDRESS.getDictId()));
            drugPerson.setResidenceAddress(getRandomAddrDetail());
            drugPerson.setFindDate(getTime());
            drugPerson.setManageType(getRandomDictValue(GKQK.getDictId()));
            drugPerson.setManagerName(RandomStringUtils.random(4, 0x4e00, 0x9fa5, false,false));
            drugPerson.setManagerAddress(mockPhone());
            drugPerson.setHelpeComment(getRandomDictValue(BFQK.getDictId()));
            drugPerson.setHelperName(mockNameCn(3));
            drugPerson.setHelperAddress(mockPhone());
            drugPerson.setIsPedigree(String.valueOf(RandomUtils.nextInt(0, 2)));
            drugPerson.setCrimeComment(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            drugPerson.setDrugReason(getRandomDictValue(XDYY.getDictId()));
            drugPerson.setDrugResult(getRandomDictValue(XDHG.getDictId()));
            drugPerson.setEffDate(getTime());
            drugPerson.setExpDate(getTime());
            drugPerson.setStatus("1");
            drugPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            drugPerson.setOperName(mockNameCn(2));
            drugPerson.setOperDate(getTime());
            drugPerson.setCreator(i.getId());
            drugPerson.setCreateTime(getTime());
            drugPerson.setUnitCode(i.getDept().getCode());
            drugPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            drugPerson.setNativeInfoAddr(getRandomAddrDetail());
            drugPerson.setServicePlaceCode(getRandomDictValue(ADDRESS.getDictId()));
            drugPersonReposity.save(drugPerson);
        });
    }

    @Test
    public void genPsychosisPerson() {
        PsychosisPerson psychosisPerson = new PsychosisPerson();
        userList.forEach(i -> {
            psychosisPerson.setPsychosisId(UUID.randomUUID().toString());
            psychosisPerson.setPersonName(mockNameCn(3));
            psychosisPerson.setUsedName(mockNameCn(3));
            psychosisPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            psychosisPerson.setDateBirth(getTime());
            psychosisPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            psychosisPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            psychosisPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            psychosisPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            psychosisPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            psychosisPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            psychosisPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            psychosisPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            psychosisPerson.setServicePlace(getRandomAddrDetail());
            psychosisPerson.setContact(mockPhone());
            psychosisPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            psychosisPerson.setRegisteredAddress(getRandomAddrDetail());
            psychosisPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            psychosisPerson.setResidenceCode(getRandomDictValue(ADDRESS.getDictId()));
            psychosisPerson.setResidenceAddress(getRandomAddrDetail());
            psychosisPerson.setSourceIncome(getRandomDictValue(JTJJZK.getDictId()));
            psychosisPerson.setIsBasicliving(String.valueOf(RandomUtils.nextInt(0, 2)));
            psychosisPerson.setGuarderIdentity(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            psychosisPerson.setGuarderName(mockNameCn(3));
            psychosisPerson.setGuarderAddress(mockPhone());
            psychosisPerson.setAttackDate(getTime());
            psychosisPerson.setDiagnoseType(getRandomDictValue(MQZDLX.getDictId()));
            psychosisPerson.setIsTrouble(String.valueOf(RandomUtils.nextInt(0, 2)));
            psychosisPerson.setTroubleTimes(RandomUtils.nextInt(1, 4));
            psychosisPerson.setLastTrouble(getTime().toString());
            psychosisPerson.setRiskLevel(getRandomDictValue(MQWXXPGDJ.getDictId()));
            psychosisPerson.setTreatFlag(getRandomDictValue(ZLQK.getDictId()));
            psychosisPerson.setReatHospital(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "医院");
            psychosisPerson.setInhospitalReason(getRandomDictValue(SSZYZLYY.getDictId()));
            psychosisPerson.setReviceUnion(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "康复机构");
            psychosisPerson.setJoinManager(getRandomDictValue(CYGLRY.getDictId()));
            psychosisPerson.setHelpeFlag(getRandomDictValue(BFQK.getDictId()));
            psychosisPerson.setEffDate(getTime());
            psychosisPerson.setExpDate(getTime());
            psychosisPerson.setStatus("1");
            psychosisPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            psychosisPerson.setOperName(mockNameCn(2));
            psychosisPerson.setOperDate(getTime());
            psychosisPerson.setCreator(i.getId());
            psychosisPerson.setCreateTime(getTime());
            psychosisPerson.setUnitCode(i.getDept().getCode());
            psychosisPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            psychosisPerson.setNativeInfoAddr(getRandomAddrDetail());
            psychosisPerson.setServicePlaceCode(getRandomDictValue(ADDRESS.getDictId()));
            psychosisPersonReposity.save(psychosisPerson);
        });
    }

    @Test
    public void genReleasedPerson() {
        ReleasedPerson releasedPerson = new ReleasedPerson();
        userList.forEach(i -> {
            releasedPerson.setReleasedId(UUID.randomUUID().toString());
            releasedPerson.setPersonName(mockNameCn(3));
            releasedPerson.setUsedName(mockNameCn(3));
            releasedPerson.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            releasedPerson.setDateBirth(getTime());
            releasedPerson.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            releasedPerson.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            releasedPerson.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            releasedPerson.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            releasedPerson.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            releasedPerson.setEduLevel(getRandomDictValue(XUE_LI.getDictId()));
            releasedPerson.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            releasedPerson.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "职业");
            releasedPerson.setServicePlace(getRandomCode(4));
            releasedPerson.setContact(mockPhone());
            releasedPerson.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            releasedPerson.setRegisteredAddress(getRandomAddrDetail());
            releasedPerson.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            releasedPerson.setResidenceCode(getRandomDictValue(ADDRESS.getDictId()));
            releasedPerson.setResidenceAddress(getRandomAddrDetail());
            releasedPerson.setIsPedigree(String.valueOf(RandomUtils.nextInt(0, 2)));
            releasedPerson.setChargeComments(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            releasedPerson.setPrisonTerm(RandomUtils.nextInt(1, 100) + "年");
            releasedPerson.setDetainUnion(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "刑场");
            releasedPerson.setPrisonEnddate(getTime());
            releasedPerson.setRiskType(getRandomDictValue(WXXPGLX.getDictId()));
            releasedPerson.setJoinDate(getTime());
            releasedPerson.setJoinFlag(getRandomDictValue(XJQK.getDictId()));
            releasedPerson.setArrangeDate(getTime());
            releasedPerson.setArrangeFlag(getRandomDictValue(AZQK.getDictId()));
            releasedPerson.setUnarrangeReason(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            releasedPerson.setHelpeComment(getRandomDictValue(BFQK.getDictId()));
            releasedPerson.setIsAgain(String.valueOf(RandomUtils.nextInt(0, 2)));
            releasedPerson.setAgainCharge(getRandomDictValue(AJLX.getDictId()) + "案");
            releasedPerson.setEffDate(getTime());
            releasedPerson.setExpDate(getTime());
            releasedPerson.setStatus("1");
            releasedPerson.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            releasedPerson.setOperName(mockNameCn(2));
            releasedPerson.setOperDate(getTime());
            releasedPerson.setCreator(i.getId());
            releasedPerson.setCreateTime(getTime());
            releasedPerson.setUnitCode(i.getDept().getCode());
            releasedPerson.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            releasedPerson.setNativeInfoAddr(getRandomAddrDetail());
            releasedPerson.setServicePlaceCode(getRandomDictValue(ADDRESS.getDictId()));
            releasedPersonReposity.save(releasedPerson);
        });
    }


}
