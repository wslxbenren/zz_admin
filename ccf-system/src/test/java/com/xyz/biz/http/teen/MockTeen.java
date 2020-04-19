package com.xyz.biz.http.teen;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.teenager.entity.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.teenager.repo.BizTeenagerBaseinfoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

import static com.xyz.modules.system.util.DictEnum.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockTeen extends MockBase {
    @Resource
    private BizTeenagerBaseinfoRepository bizTeenagerBaseinfoRepository;

    @Test
    public void genTeen() {
        BizTeenagerBaseinfo teenagerBaseinfo = new BizTeenagerBaseinfo();
        userList.forEach(i -> {
            teenagerBaseinfo.setTeenId(UUID.randomUUID().toString());
            teenagerBaseinfo.setIdentityNum(getIdNo(RandomUtils.nextBoolean()));
            teenagerBaseinfo.setPersonName(mockNameCn(3));
            teenagerBaseinfo.setUsedName(mockNameCn(3));
            teenagerBaseinfo.setPersonSex(getRandomDictValue(XING_BIE.getDictId()));
            teenagerBaseinfo.setDateBirth(getTime());
            teenagerBaseinfo.setNation(getRandomDictValue(MIN_ZU.getDictId()));
            teenagerBaseinfo.setNativeInfo(getRandomDictValue(ADDRESS.getDictId()));
            teenagerBaseinfo.setMarriageFlag(getRandomDictValue(HYZK.getDictId()));
            teenagerBaseinfo.setPartyFlag(getRandomDictValue(ZZMM.getDictId()));
            teenagerBaseinfo.setEducationBg(getRandomDictValue(XUE_LI.getDictId()));
            teenagerBaseinfo.setFaithType(getRandomDictValue(ZJXY.getDictId()));
            teenagerBaseinfo.setVocationCode(getRandomDictValue(ZYLB.getDictId()));
            teenagerBaseinfo.setVocation(RandomStringUtils.random(5, 0x4e00, 0x9fa5, false,false));
            teenagerBaseinfo.setServiceAddr(getRandomAddrDetail());
            teenagerBaseinfo.setContact(mockPhone());
            teenagerBaseinfo.setRegisteredPlace(getRandomDictValue(ADDRESS.getDictId()));
            teenagerBaseinfo.setRegisteredAddr(getRandomAddrDetail());
            teenagerBaseinfo.setResidence(getRandomDictValue(ADDRESS.getDictId()));
            teenagerBaseinfo.setResidenceAddr(getRandomAddrDetail());
            teenagerBaseinfo.setPeopleType(getRandomDictValue(RYLX.getDictId()));
            teenagerBaseinfo.setHomeSitu(getRandomDictValue(JTQK.getDictId()));
            teenagerBaseinfo.setGuardianIdno(getIdNo(RandomUtils.nextBoolean()));
            teenagerBaseinfo.setGuardianName(mockNameCn(3));
            teenagerBaseinfo.setGuardianRela(getRandomDictValue(YHZGX.getDictId()));
            teenagerBaseinfo.setGuardianMobile(mockPhone());
            teenagerBaseinfo.setGuardianAddr(getRandomAddrDetail());
            teenagerBaseinfo.setIfIllegal(RandomUtils.nextInt(0, 2));
            teenagerBaseinfo.setIllegalSitu(getRandomDictValue(AJLX.getDictId()));
            teenagerBaseinfo.setHelpeName(mockNameCn(3));
            teenagerBaseinfo.setHelpeMobile(mockPhone());
            teenagerBaseinfo.setHelpeMethod(getRandomDictValue(BFSD.getDictId()));
            teenagerBaseinfo.setHelpeComment(RandomStringUtils.random(33, 0x4e00, 0x9fa5, false,false));
            teenagerBaseinfo.setEffDate(getTime());
            teenagerBaseinfo.setExpDate(getTime());
            teenagerBaseinfo.setStatus("1");
            teenagerBaseinfo.setStatusCd(getRandomDictValue(SJZT.getDictId()));
            teenagerBaseinfo.setOperName(mockNameCn(2));
            teenagerBaseinfo.setOperDate(getTime());
            teenagerBaseinfo.setCreator(i.getId());
            teenagerBaseinfo.setCreateTime(getTime());
            teenagerBaseinfo.setUnitCode(i.getDept().getCode());
            teenagerBaseinfo.setNativeInfoAddr(getRandomAddrDetail());
            teenagerBaseinfo.setServicePlaceCode(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            teenagerBaseinfo.setGuardianAddrcode(String.valueOf(RandomUtils.nextInt(1000, 100000)));
            bizTeenagerBaseinfoRepository.save(teenagerBaseinfo);
        });
    }
}
