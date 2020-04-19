package com.xyz.biz.http.actual;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.actual.entity.*;
import com.xyz.modules.biz.service.actual.repo.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.xyz.modules.system.util.DictEnum.*;

/**
 * 实有人口生成测试数据模块
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockActual extends MockBase {
    @Resource
    private FloatpeopleRepository floatpeopleRepository;

    @Resource
    private ForeignersRepository foreignersRepository;

    @Resource
    private LeftbehindRepository leftbehindRepository;

    @Resource
    private RegistpeopleRepository registpeopleRepository;

    @Resource
    private RentalhouseRepository rentalhouseRepository;

    @Test
    public void mock() {
//        testFloatpeople();
//        testForeigners();
        testLeftbehind();
        testRegistpeople();
        testRentalhouse();
    }

    /**
     * 流动人口
     */
    @Test
    public void testFloatpeople() {
        Floatpeople floatpeople = new Floatpeople();
        userList.forEach(i -> {
            floatpeople.setFloatId(UUID.randomUUID().toString());
            floatpeople.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            floatpeople.setPersonName(mockNameCn(3));
            floatpeople.setUsedName(mockNameCn(3));
            floatpeople.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setDateBirth(Timestamp.valueOf(LocalDateTime.now()));
            floatpeople.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            floatpeople.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            floatpeople.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            floatpeople.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            floatpeople.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            floatpeople.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            floatpeople.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            floatpeople.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "职业");
            floatpeople.setServiceAddr(getRandomDictValue(ADDRESS.getDictId()));
            floatpeople.setContact(mockPhone());
            floatpeople.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            floatpeople.setRegisteredAddr(getRandomAddrDetail());
            floatpeople.setResidence(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setResidenceAddr(getRandomAddrDetail());
            floatpeople.setIntoCause(getRandomDictValue(LRYY.getDictId()));
            floatpeople.setCardType(getRandomDictValue(BZLX.getDictId()));
            floatpeople.setCardNo(getIdNo(RandomUtils.nextBoolean()));
            floatpeople.setRegisDate(Timestamp.valueOf(LocalDateTime.now()));
            floatpeople.setExpiryDate(Timestamp.valueOf(LocalDateTime.now()));
            floatpeople.setResidType(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setIfImport(RandomUtils.nextInt(0, 2));
            floatpeople.setEffDate(Timestamp.valueOf(LocalDateTime.now()));
            floatpeople.setExpDate(Timestamp.valueOf(LocalDateTime.now()));
            floatpeople.setStatus("1");
            floatpeople.setStatusCd(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setOperName(mockNameEn(2));
            floatpeople.setOperDate(getTime());
            floatpeople.setCreator(i.getId());
            floatpeople.setCreateTime(getTime());
            floatpeople.setUnitCode(i.getDept().getCode());
            floatpeople.setNativeInfoAddr(getRandomAddrDetail());
            floatpeople.setServicePlaceCode(getRandomCode(4));
            floatpeopleRepository.save(floatpeople);
        });
    }

    /**
     * 外籍
     */
    @Test
    public void testForeigners() {
        Foreigners foreigners = new Foreigners();
        userList.forEach(i -> {
            foreigners.setForeId(UUID.randomUUID().toString());
            foreigners.setLastname(mockNameEn(5));
            foreigners.setFirstname(mockNameEn(6));
            foreigners.setChinesename(mockNameCn(3));
            foreigners.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            foreigners.setDateBirth(getTime());
            foreigners.setCountry(getRandomDictValue(GJ_DQ.getDictId()));
            foreigners.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            foreigners.setCardType(getRandomDictValue(BZLX.getDictId()));
            foreigners.setCardNo(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            foreigners.setValidDate(getTime());
            foreigners.setContact(mockPhone());
            foreigners.setGoalIn(getRandomDictValue(LHMD.getDictId()));
            foreigners.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            foreigners.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "职业");
            foreigners.setServiceAddr(getRandomAddrDetail());
            foreigners.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            foreigners.setResidenceAddr(getRandomAddrDetail());
            foreigners.setArrivalDate(getTime());
            foreigners.setPlanLeave(getTime());
            foreigners.setIfImport(RandomUtils.nextInt(0, 2));
            foreigners.setEffDate(getTime());
            foreigners.setExpDate(getTime());
            foreigners.setStatus("1");
            foreigners.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            foreigners.setOperName(mockNameEn(2));
            foreigners.setOperDate(getTime());
            foreigners.setCreator(i.getId());
            foreigners.setCreateTime(getTime());
            foreigners.setUnitCode(i.getDept().getCode());
            foreigners.setServicePlaceCode(getRandomCode(4));
            foreignersRepository.save(foreigners);
        });
    }

    /**
     * 留守人员
     */
    public void testLeftbehind() {
        Leftbehind leftbehind = new Leftbehind();
        userList.forEach(i -> {
            leftbehind.setLeftId(UUID.randomUUID().toString());
            leftbehind.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            leftbehind.setPersonName(mockNameCn(3));
            leftbehind.setUsedName(mockNameCn(3));
            leftbehind.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            leftbehind.setDateBirth(Timestamp.valueOf(LocalDateTime.now()));
            leftbehind.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            leftbehind.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            leftbehind.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            leftbehind.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            leftbehind.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            leftbehind.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            leftbehind.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            leftbehind.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "职业");
            leftbehind.setServiceAddr(getRandomDictValue(ADDRESS.getDictId()));
            leftbehind.setContact(mockPhone());
            leftbehind.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            leftbehind.setRegisteredAddr(getRandomAddrDetail());
            leftbehind.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            leftbehind.setResidenceAddr(getRandomAddrDetail());
            leftbehind.setHealthy("健康");
            leftbehind.setAnnualPerincome(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            leftbehind.setHouseholdId(getRandomDictValue(RHYZBS.getDictId()));
//            leftbehind.setLeftbehindType();
            leftbehind.setMainmemIdno(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            leftbehind.setMainmemName(mockNameCn(3));
            leftbehind.setMainmemHealth(getRandomDictValue(JKZK.getDictId()));
            leftbehind.setMainmemRela(getRandomDictValue(YHZGX.getDictId()));
            leftbehind.setMainmemMobile(mockPhone());
            leftbehind.setMainmemAddr(getRandomAddrDetail());
            leftbehind.setAnnualIncome(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            leftbehind.setDemand(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "困难");
            leftbehind.setHelpeComment(getRandomDictValue(BFQK.getDictId()));
            leftbehind.setEffDate(getTime());
            leftbehind.setExpDate(getTime());
            leftbehind.setStatus("1");
            leftbehind.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            leftbehind.setOperName(mockNameEn(2));
            leftbehind.setOperDate(getTime());
            leftbehind.setCreator(i.getId());
            leftbehind.setCreateTime(getTime());
            leftbehind.setNativeInfoAddr(getRandomAddrDetail());
            leftbehind.setServicePlaceCode(getRandomCode(4));
            leftbehind.setMainmemAddrcode(getRandomCode(4));
            leftbehind.setUnitCode(i.getDept().getCode());

            leftbehindRepository.save(leftbehind);
        });
    }

    /**
     * 户籍人口
     */
    public void testRegistpeople() {
        Registpeople registpeople = new Registpeople();
        userList.forEach(i -> {
            registpeople.setRegisId(UUID.randomUUID().toString());
            registpeople.setIdentityNum(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            registpeople.setPersonName(mockNameCn(3));
            registpeople.setUsedName(mockNameCn(3));
            registpeople.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            registpeople.setDateBirth(getTime());
            registpeople.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            registpeople.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            registpeople.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            registpeople.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            registpeople.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            registpeople.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            registpeople.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            registpeople.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "职业");
            registpeople.setServiceAddr(getRandomDictValue(ADDRESS.getDictId()));
            registpeople.setContact(mockPhone());
            registpeople.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            registpeople.setRegisteredAddr(getRandomAddrDetail());
            registpeople.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            registpeople.setResidenceAddr(getRandomAddrDetail());
            registpeople.setHouseholdId(getRandomDictValue(RHYZBS.getDictId()));;
            registpeople.setDoorNo(getRandomCode(4));
            registpeople.setHouseheadIdno(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            registpeople.setHouseheadName(mockNameCn(3));
            registpeople.setHouseheadRela(getRandomDictValue(YHZGX.getDictId()));
            registpeople.setHouseheadMobile(mockPhone());
            registpeople.setEffDate(getTime());
            registpeople.setExpDate(getTime());
            registpeople.setStatus("1");
            registpeople.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            registpeople.setOperName(mockNameEn(2));
            registpeople.setOperDate(getTime());
            registpeople.setCreator(i.getId());
            registpeople.setCreateTime(getTime());
            registpeople.setNativeInfoAddr(getRandomAddrDetail());
            registpeople.setServicePlaceCode(getRandomCode(4));
            registpeople.setUnitCode(i.getDept().getCode());
            registpeopleRepository.save(registpeople);
        });
    }

    /**
     * 出租
     */
    public void testRentalhouse() {
        Rentalhouse rentalhouse = new Rentalhouse();
        userList.forEach(i -> {
            rentalhouse.setRentId(UUID.randomUUID().toString());
            rentalhouse.setHouseCode(getRandomCode(4));
            rentalhouse.setHouseName(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            rentalhouse.setHouseAddr(getRandomAddrDetail());
            rentalhouse.setConstrPurpose(getRandomDictValue(JZYT.getDictId()));
            rentalhouse.setConstrArea(RandomUtils.nextDouble(20, 2000));
            rentalhouse.setCardType(getRandomDictValue(ZJDM.getDictId()));
            rentalhouse.setCardNo(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            rentalhouse.setHomeownerName(mockNameCn(3));
            rentalhouse.setHomeownerMobile(mockPhone());
            rentalhouse.setHomeownerAddr(getRandomAddrDetail());
            rentalhouse.setHazardType(getRandomDictValue(YHLX.getDictId()));
            rentalhouse.setLesseeIdno(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            rentalhouse.setLesseeName(mockNameCn(3));
            rentalhouse.setLesseeMobile(mockPhone());
            rentalhouse.setLng(RandomUtils.nextDouble(20, 2000));
            rentalhouse.setLat(RandomUtils.nextDouble(20, 2000));
            rentalhouse.setEffDate(getTime());
            rentalhouse.setExpDate(getTime());
            rentalhouse.setStatus("1");
            rentalhouse.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            rentalhouse.setOperName(mockNameEn(2));
            rentalhouse.setOperDate(getTime());
            rentalhouse.setCreator(i.getId());
            rentalhouse.setCreateTime(getTime());
            rentalhouse.setUnitCode(i.getDept().getCode());
            rentalhouse.setHouseAddrcode(getRandomCode(4));
            rentalhouse.setHomeownerAddrcode(getRandomCode(4));
            rentalhouseRepository.save(rentalhouse);
        });
    }

}
