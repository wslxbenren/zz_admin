package com.xyz.system;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.audit.mongo.service.ModifyRecordsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    @Resource
    private ModifyRecordsRepo recordsRepo;

    @Test
    public void testInsert() {
        List<ModifyRecords> articles = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            articles.add(new ModifyRecords(UUID.randomUUID().toString(),
                    RandomUtil.randomString(20),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    "abcd",
                    RandomUtil.randomString(20),
                    RandomUtil.randomString(20)));
        }
        recordsRepo.saveAll(articles);

        System.out.println("存储完成");
        System.out.println(recordsRepo.findAll());
    }
}
