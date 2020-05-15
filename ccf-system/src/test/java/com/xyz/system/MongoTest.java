package com.xyz.system;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.audit.mongo.service.ModifyRecordsRepo;
import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import com.xyz.modules.system.service.CompareFieldsService;
import com.xyz.modules.system.service.impl.CompareFieldsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Column;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    @Resource
    private ModifyRecordsRepo recordsRepo;

    @Autowired
    private CompareFieldsService compareFieldsServiceImpl;

    @Test
    public void testInsert() {
        List<ModifyRecords> articles = Lists.newArrayList();
        ModifyRecords m = new ModifyRecords();
        m.setId("123123123");
        m.setEntityId("123123123");
        recordsRepo.save(m);
        System.out.println("存储完成");
        recordsRepo.findById("123123123");
        System.out.println(recordsRepo.findById("123123123"));
    }

    @Test
    public void testFind() {
        Floatpeople floatpeopleA = new Floatpeople();
        floatpeopleA.setFloatId("123");
        floatpeopleA.setCardNo("01");
        floatpeopleA.setFaithType("20");
        floatpeopleA.setResidence("110102");
        Floatpeople floatpeopleB = new Floatpeople();
        floatpeopleB.setFloatId("123");
        floatpeopleB.setCardNo("02");
        floatpeopleB.setFaithType("10");
        floatpeopleB.setResidence("110105");
        String test = compareFieldsServiceImpl.compareModifyRecords(floatpeopleA, floatpeopleB, new String[]{"floatId"});
        System.out.println(test);

    }




}
