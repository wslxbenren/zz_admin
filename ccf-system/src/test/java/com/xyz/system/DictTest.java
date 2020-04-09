package com.xyz.system;

import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.DictService;
import com.xyz.modules.system.service.dto.DictDetailQueryCriteria;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 字典测试类
 * @author dadovicn
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class DictTest {
    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private DictService dictService;

    @Test
    public void testFindDictDetailLabel() {
        DictDetail detail = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), "01");
        assert detail.getLabel().equals("汉族");
    }

    /**
     * 测试获取字典二级数据
     */
    @Test
    public void testFind2LevelDict() {
        List<Dict> dictList = dictService.get2LevelDict();
        dictList.forEach(i -> {
            assert i.getDictDetails().size() > 0;
        });
    }

    public void testFindDictDetailByPid() {
        DictDetailQueryCriteria d = new DictDetailQueryCriteria();
        d.setPId("5411");
        PageRequest pageRequest = new PageRequest(0, 10);

        assert dictDetailService.queryAll(d, pageRequest).size() == 10;
    }
}
