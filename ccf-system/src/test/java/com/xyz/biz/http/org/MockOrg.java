package com.xyz.biz.http.org;

import com.xyz.biz.http.MockBase;
import com.xyz.gen.MockUtil;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import com.xyz.modules.biz.service.org.entity.ManagecenterInfo;
import com.xyz.modules.biz.service.org.entity.ManageleadresponsInfo;
import com.xyz.modules.biz.service.org.repo.BuildheadInfoRepository;
import com.xyz.modules.biz.service.org.repo.MajorcaseInfoRepository;
import com.xyz.modules.biz.service.org.repo.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.org.repo.ManageleadresponsInfoRepository;
import com.xyz.modules.system.domain.User;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.xyz.modules.system.util.DictEnum.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockOrg extends MockBase {

    @Resource
    private BuildheadInfoRepository buildheadInfoRepository;

    @Resource
    private MajorcaseInfoRepository majorcaseInfoRepository;

    @Resource
    private ManagecenterInfoRepository managecenterInfoRepository;

    @Resource
    private ManageleadresponsInfoRepository manageleadresponsInfoRepository;

    @Test
    public void init() {
        genBuildheadInfo();
        genMajorcaseInfo();
        genMajorcenterInfo();
        ManageleadreponsInfo();
    }

    /**
     * 楼栋长
     */
    public void genBuildheadInfo() {
        AtomicInteger a = new AtomicInteger(0);
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
            buildheadInfo.setSex(getRandomDictValue(XING_BIE.getDictId()));
            buildheadInfo.setNational(getRandomDictValue(MIN_ZU.getDictId()));
            buildheadInfo.setPoliticalStatus(getRandomDictValue(ZZMM.getDictId()));
            buildheadInfo.setBirth(getTime());
            buildheadInfo.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            buildheadInfo.setMobile(MockUtil.mockPhone());
            buildheadInfo.setFixedPhone(MockUtil.mockPhone());
            buildheadInfo.setAddr(getRandomDictValue(ADDRESS.getDictId()));
            buildheadInfo.setAddrDetail(MockUtil.mockCommunityStr());
            buildheadInfo.setLng(random.nextDouble());
            buildheadInfo.setLat(random.nextDouble());
            buildheadInfo.setCreator(i.getId());
            buildheadInfo.setCreateTime(getTime());
            buildheadInfo.setModifier(i.getId());
            buildheadInfo.setUpdateTime(getTime());
            buildheadInfo.setUnitCode(i.getDept().getCode());
            a.incrementAndGet();
            buildheadInfoRepository.save(buildheadInfo);
        });
    }

    /**
     * 重大事件
     */
    public void genMajorcaseInfo() {
        MajorcaseInfo majorcaseInfo = new MajorcaseInfo();
        userList.forEach(i -> {
            majorcaseInfo.setId(UUID.randomUUID().toString());
            majorcaseInfo.setCaseCode(getRandomCode(4));
            majorcaseInfo.setCaseName(RandomStringUtils.random(20, 0x4e00, 0x9fa5, false,false) + "案件");
            majorcaseInfo.setOccurDate(getTime());
            majorcaseInfo.setOccurAddr(getRandomDictValue(ADDRESS.getDictId()));
            majorcaseInfo.setOccurAddrdetail(getRandomAddrDetail());
            majorcaseInfo.setCaseGrage(getRandomDictValue(AJFJ.getDictId()));
            majorcaseInfo.setCaseType(getRandomDictValue(AJLX.getDictId()));
            majorcaseInfo.setCaseInfo(RandomStringUtils.random(20, 0x4e00, 0x9fa5, false,false));
            majorcaseInfo.setCreator(i.getId());
            majorcaseInfo.setCreateTime(getTime());
            majorcaseInfo.setModifier(i.getId());
            majorcaseInfo.setUpdateTime(getTime());
            majorcaseInfo.setUnitCode(i.getDept().getCode());
            majorcaseInfo.setEffDate(getTime());
            majorcaseInfo.setExpDate(getTime());
            majorcaseInfo.setStatus(String.valueOf(RandomUtils.nextInt(0, 2)));
            majorcaseInfo.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            majorcaseInfoRepository.save(majorcaseInfo);
        });

    }

    /**
     * 综治中心
     */
    public void genMajorcenterInfo() {
        ManagecenterInfo managecenterInfo = new ManagecenterInfo();
        userList.forEach(i -> {
            managecenterInfo.setId(UUID.randomUUID().toString());
            managecenterInfo.setCenterCode(getRandomCode(4));
            managecenterInfo.setCenterName(RandomStringUtils.random(2, 0x4e00, 0x9fa5, false,false) + "中心");
            managecenterInfo.setCenterMobile(mockPhone());
            managecenterInfo.setGrage(getRandomDictValue(JGCJ.getDictId()));
            managecenterInfo.setUsername(mockNameCn(3));
            managecenterInfo.setUsercode(getRandomCode(4));
            managecenterInfo.setUserMobile(mockPhone());
            managecenterInfo.setDeptId(i.getDept().getId());
            managecenterInfo.setAddr(getRandomDictValue(ADDRESS.getDictId()));
            managecenterInfo.setAddrDetail(getRandomAddrDetail());
            managecenterInfo.setLng(RandomUtils.nextDouble(5d, 100d));
            managecenterInfo.setLat(RandomUtils.nextDouble(5d, 100d));
            managecenterInfo.setCreator(i.getCreator());
            managecenterInfo.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            managecenterInfo.setModifier(i.getId());
            managecenterInfo.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            managecenterInfo.setUnitCode(i.getDept().getCode());
            managecenterInfoRepository.save(managecenterInfo);
        });
    }

    /**
     * 领导责任制
     */
    public void ManageleadreponsInfo() {
        ManageleadresponsInfo manageleadresponsInfo = new ManageleadresponsInfo();
        userList.forEach(i -> {
            manageleadresponsInfo.setId(UUID.randomUUID().toString());
            manageleadresponsInfo.setImpledareaCode(getRandomCode(4));
            manageleadresponsInfo.setImpledareaName(mockNameCn(3));
            manageleadresponsInfo.setAreaGrage(getRandomDictValue(JGCJ.getDictId()));
            manageleadresponsInfo.setImplementerName(mockNameCn(3));
            manageleadresponsInfo.setImplementerGrage(getRandomDictValue(JGCJ.getDictId()));
            manageleadresponsInfo.setPolicyType(getRandomDictValue(ZCZL.getDictId()));
            manageleadresponsInfo.setCreator(i.getId());
            manageleadresponsInfo.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            manageleadresponsInfo.setModifier(i.getId());
            manageleadresponsInfo.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            manageleadresponsInfo.setUnitCode(i.getDept().getCode());
            manageleadresponsInfo.setStatus(String.valueOf(RandomUtils.nextInt(0, 2)));
            manageleadresponsInfo.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            manageleadresponsInfoRepository.save(manageleadresponsInfo);
        });
    }



}
