package com.xyz.biz;

import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.RentalhouseService;
import com.xyz.modules.biz.service.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalhouseApplicationTests {
    
    @Autowired
    private RentalhouseService rentalhouseService;
 
    @Test
    public void testAdd() {
        for (int i = 0; i < 5; i++) {
            Rentalhouse rentalhouse = new Rentalhouse();
            rentalhouse.setRentId(UUID.randomUUID().toString());
            rentalhouse.setHouseCode("123");
            rentalhouse.setHouseName("马云");
            rentalhouse.setHouseAddr("杭州西湖");
            rentalhouse.setConstrPurpose("商用");
            rentalhouse.setConstrArea(888.8);
            rentalhouse.setCardType("1");
            rentalhouse.setCardNo("11010119900307715X");
            rentalhouse.setHomeownerName("马化腾");
            rentalhouse.setHomeownerMobile("057188888888");
            rentalhouse.setHomeownerAddr("广东深圳");
            rentalhouse.setHazardType("2");
            rentalhouse.setLesseeIdno("3");
            rentalhouse.setLesseeName("刘德华");
            rentalhouse.setLesseeMobile("13888888888");
            rentalhouse.setLng(3.3);
            rentalhouse.setLat(4.3);
            rentalhouse.setEffDate(new Timestamp(new Date().getTime()));
            rentalhouse.setExpDate(new Timestamp(new Date().getTime()));
            rentalhouse.setStatus("1");
            rentalhouse.setStatusCd("2");
            rentalhouse.setOperName("管理员");
            rentalhouse.setOperDate(new Timestamp(new Date().getTime()));
            rentalhouse.setCreator("管理员");
            rentalhouse.setUnitCode("0355");
            rentalhouse.setHouseAddrcode("45001");
            rentalhouse.setHomeownerAddrcode("00332");
            rentalhouseService.create(rentalhouse);
        }

    }
    @Test
    public void testUpdate() {
        Rentalhouse rentalhouse = new Rentalhouse();
        rentalhouse.setRentId("e230da2550b542c184acc54684204fb7");
        rentalhouse.setHouseCode("1231");
        rentalhouse.setHouseName("马云1");
        rentalhouse.setHouseAddr("杭州西湖1");
        rentalhouse.setConstrPurpose("商1");
        rentalhouse.setConstrArea(888.8);
        rentalhouse.setCardType("11");
        rentalhouse.setCardNo("110101199003077151");
        rentalhouse.setHomeownerName("马化腾1");
        rentalhouse.setHomeownerMobile("057188888881");
        rentalhouse.setHomeownerAddr("广东深圳1");
        rentalhouse.setHazardType("21");
        rentalhouse.setLesseeIdno("31");
        rentalhouse.setLesseeName("刘德华1");
        rentalhouse.setLesseeMobile("13888888881");
        rentalhouse.setLng(3.1);
        rentalhouse.setLat(4.1);
        rentalhouse.setEffDate(new Timestamp(new Date().getTime()));
        rentalhouse.setExpDate(new Timestamp(new Date().getTime()));
        rentalhouse.setStatus("11");
        rentalhouse.setStatusCd("21");
        rentalhouse.setOperName("管理员1");
        rentalhouse.setOperDate(new Timestamp(new Date().getTime()));
        rentalhouse.setCreator("管理员1");
        rentalhouse.setUnitCode("03551");
        rentalhouse.setHouseAddrcode("450011");
        rentalhouse.setHomeownerAddrcode("003321");
        rentalhouseService.update(rentalhouse);
    }
    @Test
    public void testDelete() {
        rentalhouseService.delete("1d2205c4876a4ca981ede0650a4e9726");
    }
    @Test
    public void testFind() {
        RentalhouseDTO r = rentalhouseService.findById("e230da2550b542c184acc54684204fb7");
        RentalhouseQueryCriteria rq = new RentalhouseQueryCriteria();
        rq.setCreateTime(Arrays.asList("2020-02-02 14:13:47","2020-04-10 12:13:47"));
        rq.setHomeownerName("1");
        rq.setHouseCode("1");

        rq.setCreateTime(Arrays.asList("2020-02-02 14:13:47","2020-04-10 12:13:47"));
        rq.setCreator("管理员");
        Object o = rentalhouseService.queryAll(rq);
    }
}

