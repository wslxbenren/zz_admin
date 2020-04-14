package com.xyz;
/**
 * 作者：xjh
 * 时间：2020-4-9 15:14:43
 */
import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.modules.biz.domain.Leftbehind;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
import com.xyz.modules.biz.service.dto.LeftbehindQueryCriteria;
import com.xyz.modules.biz.service.impl.ForeignersServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForeignersTests {

    @Autowired
    private ForeignersServiceImpl foreignersServiceImpl;

    @Test
    public void ForeignersAdd() throws Exception {
        //实体类
        Foreigners info = new Foreigners();
        info.setCreator("xjh");
        info.setUnitCode("11");
        info.setContact("562");
        info.setDateBirth(new Timestamp(new Date().getTime()));
        info.setEffDate(new Timestamp(new Date().getTime()));
        info.setExpDate(new Timestamp(new Date().getTime()));
        info.setFaithType("1");
        info.setPersonSex("1");
        info.setResidence("1");
        info.setServiceAddr("1");
        info.setServicePlaceCode("1");
        info.setStatus("1");
        info.setStatusCd("1");
        info.setVocation("1");
        info.setVocationCode("1");
        info.setArrivalDate(new Timestamp(new Date().getTime()));
        info.setCardNo("1");
        info.setCardType("1");
        info.setChinesename("xjh");
        info.setCountry("1");
        info.setFirstname("12");
        info.setGoalIn("1");
        info.setIfImport(1);
        info.setLastname("12");
        info.setPlanLeave(new Timestamp(new Date().getTime()));
        info.setResidenceAddr("1");
        info.setValidDate(new Timestamp(new Date().getTime()));
        foreignersServiceImpl.create(info);
    }

    @Test
    public void ForeignersUpdate() throws Exception {
        Foreigners info = new Foreigners();
        info.setOperName("邢家华");
        info.setUnitCode("11");
        info.setContact("562");
        info.setDateBirth(new Timestamp(new Date().getTime()));
        info.setEffDate(new Timestamp(new Date().getTime()));
        info.setExpDate(new Timestamp(new Date().getTime()));
        info.setFaithType("1");
        info.setPersonSex("1");
        info.setResidence("1");
        info.setServiceAddr("1");
        info.setServicePlaceCode("1");
        info.setStatus("1");
        info.setStatusCd("1");
        info.setVocation("1");
        info.setVocationCode("1");
        info.setArrivalDate(new Timestamp(new Date().getTime()));
        info.setCardNo("1");
        info.setCardType("1");
        info.setChinesename("xjh");
        info.setCountry("1");
        info.setFirstname("12");
        info.setGoalIn("1");
        info.setIfImport(1);
        info.setLastname("12");
        info.setPlanLeave(new Timestamp(new Date().getTime()));
        info.setResidenceAddr("1");
        info.setValidDate(new Timestamp(new Date().getTime()));
        info.setForeId("d38100042986482c9ba49179b89f102e");
        foreignersServiceImpl.update(info);
    }

    @Test
    public void ForeignersDelete() throws Exception {
        String id="d38100042986482c9ba49179b89f102e";
        foreignersServiceImpl.delete(id);

    }
    @Test
    public void ForeignersDetails() throws Exception {
        String id="d38100042986482c9ba49179b89f102e";
        foreignersServiceImpl.findById(id);

    }

    @Test
    public void ForeignersQuery() throws Exception {
        ForeignersQueryCriteria criteria = new ForeignersQueryCriteria();
       // criteria.setCreator("邢家华");
        List<String> createTime = new ArrayList<String>();
        createTime.add("2020-4-9 14:48:41");
        createTime.add("2020-4-9 15:05:35");
        criteria.setCreateTime(createTime);
        foreignersServiceImpl.queryAll(criteria);
    }
}
