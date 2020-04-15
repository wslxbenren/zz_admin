package com.xyz.biz;

import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import com.xyz.modules.biz.service.actual.repo.FloatpeopleRepository;
import com.xyz.modules.biz.service.actual.FloatpeopleService;
import com.xyz.modules.biz.service.actual.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.FloatpeopleQueryCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FloatpeopleApplicationTests {
    @Autowired
    FloatpeopleRepository floatpeopleRepository;
    @Autowired
    FloatpeopleService floatpeopleService;
    @Test
    public void testAdd() {
        for (int i = 0; i < 5 ; i++) {
            Floatpeople floatpeople = new Floatpeople();
            floatpeople.setFloatId(UUID.randomUUID().toString());
            Random random = new Random();
            int ends = random.nextInt(99);
            String.format("%02d",ends);
            floatpeople.setIdentityNum("1101011990030740"+ String.format("%02d",ends));
            floatpeople.setPersonName("王五");
            floatpeople.setUsedName("问问");
            floatpeople.setPersonSex("男");
            floatpeople.setDateBirth(new Timestamp(new Date().getTime()));
            floatpeople.setNation("汉族");
            floatpeople.setNativeInfo("河南省");
            floatpeople.setMarriageFlag("未婚");
            floatpeople.setPartyFlag("党员");
            floatpeople.setEducationBg("本科");
            floatpeople.setFaithType("无");
            floatpeople.setVocationCode("法师");
            floatpeople.setVocation("法师");
            floatpeople.setServiceAddr("事实上顶顶顶烦烦烦");
            floatpeople.setContact("037162666666");
            floatpeople.setRegisteredPlace("河南省郑州市");
            floatpeople.setRegisteredAddr("河南省郑州市二七区");
            floatpeople.setResidence("河南省郑州市");
            floatpeople.setResidenceAddr("河南省郑州市二七区");
            floatpeople.setIntoCause("1");
            floatpeople.setCardType("1");
            floatpeople.setCardNo("110101199003070994");
            floatpeople.setRegisDate(new Timestamp(new Date().getTime()));
            floatpeople.setExpiryDate(new Timestamp(new Date().getTime()));
            floatpeople.setResidType("别墅");
            floatpeople.setIfImport(122);
            floatpeople.setEffDate(new Timestamp(new Date().getTime()));
            floatpeople.setExpDate(new Timestamp(new Date().getTime()));
            floatpeople.setStatus("ss");
            floatpeople.setStatusCd("10");
            floatpeople.setOperName("时代");
            floatpeople.setOperDate(new Timestamp(new Date().getTime()));
            floatpeople.setCreator("管理员");
            floatpeople.setCreateTime(new Timestamp(new Date().getTime()));
            floatpeople.setUnitCode("带点");
            floatpeople.setNativeInfoAddr("北京市故宫");
            floatpeople.setServicePlaceCode("45000");
            floatpeopleService.create(floatpeople);
        }
    }
    @Test
    public void testUpdate() {
        Floatpeople floatpeople = new Floatpeople();
        floatpeople.setFloatId("ede60ffda48c45ed800b585697549ad4");
        floatpeople.setIdentityNum("110101199003000000");
        floatpeople.setPersonName("更新");
        floatpeople.setUsedName("更新");
        floatpeople.setPersonSex("女");
        floatpeople.setDateBirth(new Timestamp(new Date().getTime()));
        floatpeople.setNation("回族");
        floatpeople.setNativeInfo("更新省");
        floatpeople.setMarriageFlag("更新");
        floatpeople.setPartyFlag("更新");
        floatpeople.setEducationBg("更新");
        floatpeople.setFaithType("更新");
        floatpeople.setVocationCode("更新");
        floatpeople.setVocation("更新");
        floatpeople.setServiceAddr("更新");
        floatpeople.setContact("更新");
        floatpeople.setRegisteredPlace("更新");
        floatpeople.setRegisteredAddr("更新");
        floatpeople.setResidence("更新");
        floatpeople.setResidenceAddr("更新");
        floatpeople.setIntoCause("0");
        floatpeople.setCardType("0");
        floatpeople.setCardNo("更新");
        floatpeople.setRegisDate(new Timestamp(new Date().getTime()));
        floatpeople.setExpiryDate(new Timestamp(new Date().getTime()));
        floatpeople.setResidType("更新");
        floatpeople.setIfImport(100);
        floatpeople.setEffDate(new Timestamp(new Date().getTime()));
        floatpeople.setExpDate(new Timestamp(new Date().getTime()));
        floatpeople.setStatus("更新");
        floatpeople.setStatusCd("更新");
        floatpeople.setOperName("更新");
        floatpeople.setOperDate(new Timestamp(new Date().getTime()));
        floatpeople.setCreator("更新");
        floatpeople.setCreateTime(new Timestamp(new Date().getTime()));
        floatpeople.setUnitCode("更新");
        floatpeople.setNativeInfoAddr("更新");
        floatpeople.setServicePlaceCode("更新");
        floatpeopleService.update(floatpeople);
    }
    @Test
    public void testDelete() {
        floatpeopleService.delete("d724f4fed84c47679424f7ee881f33fc");
    }
    @Test
    public void testFind() {
        FloatpeopleDTO f = floatpeopleService.findById("ede60ffda48c45ed800b585697549ad4");
        System.out.println(f);
        FloatpeopleQueryCriteria criteria = new FloatpeopleQueryCriteria();

        criteria.setCreateTime(Arrays.asList("2020-04-09","2020-04-15"));

        criteria.setPersonSex("男");
        criteria.setNativeInfo("sdf");

        criteria.setPersonName("陈晨");
        criteria.setIdentityNum("123");
        Object o = floatpeopleService.queryAll(criteria);
    }
}

