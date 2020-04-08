package com.xyz.system;

import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.DictService;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class DictTest {
    @Autowired
    private DictDetailService dictService;

    @Test
    public void testFindDictDetailLabel() {
        DictDetail detail = dictService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), "01");
        assert detail.getLabel().equals("汉族");
    }
}
