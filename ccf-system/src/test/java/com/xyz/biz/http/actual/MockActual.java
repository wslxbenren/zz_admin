package com.xyz.biz.http.actual;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.actual.entity.*;
import com.xyz.modules.biz.service.actual.repo.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.xyz.modules.system.util.DictEnum.*;

/**
 * 实有人口生成测试数据模块
 */
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

    /**
     * 流动人口
     */
    @Test
    public void testFloatpeople() {
        Floatpeople floatpeople = new Floatpeople();
        userList.forEach(i -> {
            floatpeople.setFloatId(UUID.randomUUID().toString());
            floatpeople.setIdentityNum(getIdNo(RandomUtils.nextBoolean()));
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
            floatpeople.setServiceAddr(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setContact(mockPhone());
            floatpeople.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            floatpeople.setRegisteredAddr(getRandomAddrDetail());
            floatpeople.setResidence(getRandomDictValue(XING_BIE.getDictId()));
            floatpeople.setResidenceAddr(getRandomAddrDetail());
            floatpeople.setIntoCause(RandomStringUtils.random(30, 0x4e00, 0x9fa5, false,false) + "原由");
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
            floatpeople.setOperName(i.getId());
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
            foreigners.setCardNo(getIdNo(RandomUtils.nextBoolean()));
            foreigners.setValidDate(getTime());
            foreigners.setContact(mockPhone());
            foreigners.setGoalIn(RandomStringUtils.random(30, 0x4e00, 0x9fa5, false,false) + "原由");
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
            foreigners.setOperName(i.getId());
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
    @Test
    public void testLeftbehind() {
        Leftbehind leftbehind = new Leftbehind();
        userList.forEach(i -> {
            leftbehind.setLeftId(UUID.randomUUID().toString());
            leftbehind.setIdentityNum(getIdNo(RandomUtils.nextBoolean()));
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
            leftbehind.setServiceAddr(getRandomDictValue(XING_BIE.getDictId()));
            leftbehind.setContact(mockPhone());
            leftbehind.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            leftbehind.setRegisteredAddr(getRandomAddrDetail());
            leftbehind.setResidence(getRandomDictValue(XING_BIE.getDictId()));
            leftbehind.setResidenceAddr(getRandomAddrDetail());
            leftbehind.setHealthy("健康");
            leftbehind.setAnnualPerincome(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            leftbehind.setHouseholdId(getRandomDictValue(RHYZBS.getDictId()));
//            leftbehind.setLeftbehindType();
            leftbehind.setMainmemIdno(getIdNo(RandomUtils.nextBoolean()));
            leftbehind.setMainmemName(mockNameCn(3));
            leftbehind.setMainmemHealth("健康");
            leftbehind.setMainmemRela(getRandomDictValue(YHZGX.getDictId()));
            leftbehind.setMainmemMobile(mockPhone());
            leftbehind.setMainmemAddr(getRandomAddrDetail());
            leftbehind.setAnnualIncome(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            leftbehind.setDemand(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false) + "困难");
            leftbehind.setHelpeComment(RandomStringUtils.random(11, 0x4e00, 0x9fa5, false,false));
            leftbehind.setEffDate(getTime());
            leftbehind.setExpDate(getTime());
            leftbehind.setStatus("1");
            leftbehind.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            leftbehind.setOperName(i.getId());
            leftbehind.setOperDate(getTime());
            leftbehind.setCreator(i.getId());
            leftbehind.setCreateTime(getTime());
            leftbehind.setNativeInfoAddr(getRandomAddrDetail());
            leftbehind.setServicePlaceCode(getRandomCode(4));
            leftbehind.setMainmemAddrcode(getRandomCode(4));

            leftbehindRepository.save(leftbehind);
        });
    }

    /**
     * 户籍人口
     */
    @Test
    public void testRegistpeople() {
        Registpeople registpeople = new Registpeople();
        userList.forEach(i -> {
            registpeople.setRegisId(UUID.randomUUID().toString());
//            registpeople.setIdentityNum();
//            registpeople.setPersonName();
//            registpeople.setUsedName();
//            registpeople.setPersonSex();
//            registpeople.setDateBirth();
//            registpeople.setNation();
//            registpeople.setNativeInfo();
//            registpeople.setMarriageFlag();
//            registpeople.setPartyFlag();
//            registpeople.setEducationBg();
//            registpeople.setFaithType();
//            registpeople.setVocationCode();
//            registpeople.setVocation();
//            registpeople.setServiceAddr();
//            registpeople.setContact();
//            registpeople.setRegisteredPlace();
//            registpeople.setRegisteredAddr();
//            registpeople.setResidence();
//            registpeople.setResidenceAddr();
//            registpeople.setHouseholdId();
//            registpeople.setDoorNo();
//            registpeople.setHouseheadIdno();
//            registpeople.setHouseheadName();
//            registpeople.setHouseheadRela();
//            registpeople.setHouseheadMobile();
//            registpeople.setEffDate();
//            registpeople.setExpDate();
//            registpeople.setStatus();
//            registpeople.setStatusCd();
//            registpeople.setOperName();
//            registpeople.setOperDate();
//            registpeople.setCreator();
//            registpeople.setCreateTime();
//            registpeople.setUnitCode();
//            registpeople.setNativeInfoAddr();
//            registpeople.setServicePlaceCode();

            registpeopleRepository.save(registpeople);
        });
    }

    /**
     * 出租
     */
    @Test
    public void testRentalhouse() {
        Rentalhouse rentalhouse = new Rentalhouse();
        userList.forEach(i -> {
//            rentalhouse.setRentId(UUID.randomUUID().toString());
//            rentalhouse.setHouseCode();
//            rentalhouse.setHouseName();
//            rentalhouse.setHouseAddr();
//            rentalhouse.setConstrPurpose();
//            rentalhouse.setConstrArea();
//            rentalhouse.setCardType();
//            rentalhouse.setCardNo();
//            rentalhouse.setHomeownerName();
//            rentalhouse.setHomeownerMobile();
//            rentalhouse.setHomeownerAddr();
//            rentalhouse.setHazardType();
//            rentalhouse.setLesseeIdno();
//            rentalhouse.setLesseeName();
//            rentalhouse.setLesseeMobile();
//            rentalhouse.setLng();
//            rentalhouse.setLat();
//            rentalhouse.setEffDate();
//            rentalhouse.setExpDate();
//            rentalhouse.setStatus();
//            rentalhouse.setStatusCd();
//            rentalhouse.setOperName();
//            rentalhouse.setOperDate();
//            rentalhouse.setCreator();
//            rentalhouse.setCreateTime();
//            rentalhouse.setUnitCode();
//            rentalhouse.setHouseAddrcode();
//            rentalhouse.setHomeownerAddrcode();

            rentalhouseRepository.save(rentalhouse);
        });
    }

}
