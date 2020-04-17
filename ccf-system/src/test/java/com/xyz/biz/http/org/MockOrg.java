package com.xyz.biz.http.org;

import com.xyz.gen.MockUtil;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.repo.BuildheadInfoRepository;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.dto.DictDetailQueryCriteria;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockOrg {
    @Resource
    private DeptRepository deptService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private BuildheadInfoRepository buildheadInfoRepository;
    @Resource
    private DictDetailService dictService;

    @Test
    public void gen() {
        Random random = new Random();
        List<User> userList = userRepository.findAll();
        genBuildheadInfo(userList, random);

    }

    /**
     * 楼栋长生成测试数据
     */
    public void genBuildheadInfo(List<User> userList, Random random) {
        AtomicInteger a = new AtomicInteger(0);
        DictDetailQueryCriteria param = new DictDetailQueryCriteria();
        param.setPId("7033");
        param.setDictId("106");
        List<DictDetail> addrDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId("100");
        List<DictDetail> sexDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.MIN_ZU.getDictId()));
        List<DictDetail> nationalDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.ZZMM.getDictId()));
        List<DictDetail> politicalStatusDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.XUE_LI.getDictId()));
        List<DictDetail> educationBgDict = dictService.queryAll(param);

        userList.forEach(i -> {
            BuildheadInfo buildheadInfo = new BuildheadInfo();
            buildheadInfo.setId(UUID.randomUUID().toString());
            buildheadInfo.setVillageCode(String.valueOf(random.nextInt(1000)));
            buildheadInfo.setVillageName(MockUtil.mockNameCn(5));
            buildheadInfo.setBuildName(a.get()+ "号楼");
            buildheadInfo.setBuildArea(random.nextDouble());
            buildheadInfo.setLayerNum(random.nextInt(100));
            buildheadInfo.setUnitNum(random.nextInt(4));
            buildheadInfo.setHouseholdsNum(random.nextInt(1550));
            buildheadInfo.setPeopleNum(random.nextInt(2222));
            buildheadInfo.setHeadName(MockUtil.mockNameCn(2));
            buildheadInfo.setSex(sexDict.get(random.nextInt(sexDict.size())).getValue());
            buildheadInfo.setNational(nationalDict.get(random.nextInt(nationalDict.size())).getValue());
            buildheadInfo.setPoliticalStatus(politicalStatusDict.get(random.nextInt(politicalStatusDict.size())).getValue());
            buildheadInfo.setBirth(Timestamp.valueOf(LocalDateTime.now().minusYears(40)));
            buildheadInfo.setEducationBg(educationBgDict.get(random.nextInt(educationBgDict.size())).getValue());
            buildheadInfo.setMobile(MockUtil.mockPhone());
            buildheadInfo.setFixedPhone(MockUtil.mockPhone());
            buildheadInfo.setAddr(addrDict.get(random.nextInt(addrDict.size())).getValue());
            buildheadInfo.setAddrDetail(MockUtil.mockCommunityStr());
            buildheadInfo.setLng(random.nextDouble());
            buildheadInfo.setLat(random.nextDouble());
            buildheadInfo.setCreator(i.getId());
            buildheadInfo.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            buildheadInfo.setModifier(i.getId());
            buildheadInfo.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            buildheadInfo.setUnitCode(i.getDept().getCode());
            a.incrementAndGet();
            buildheadInfoRepository.save(buildheadInfo);
        });
    }

    /**
     * 重大事件 生成测试数据
     */
    public void genMajorcaseInfo() {
        List<User> userList = userRepository.findAll();
        AtomicInteger a = new AtomicInteger(0);
        DictDetailQueryCriteria param = new DictDetailQueryCriteria();
        param.setPId("7033");
        param.setDictId("106");
        List<DictDetail> addrDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId("100");
        List<DictDetail> sexDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.MIN_ZU.getDictId()));
        List<DictDetail> nationalDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.ZZMM.getDictId()));
        List<DictDetail> politicalStatusDict = dictService.queryAll(param);
        param.setPId(null);
        param.setDictId(String.valueOf(DictEnum.XUE_LI.getDictId()));
        List<DictDetail> educationBgDict = dictService.queryAll(param);


    }

    public void genMajorcenterInfo() {

    }

    public void ManageleadreponsInfo() {

    }

}
