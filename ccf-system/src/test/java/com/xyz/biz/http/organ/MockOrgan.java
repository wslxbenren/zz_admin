package com.xyz.biz.http.organ;

import com.xyz.biz.http.MockBase;
import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import com.xyz.modules.biz.service.organ.entity.Socialorgan;
import com.xyz.modules.biz.service.organ.repo.NonpublicRepository;
import com.xyz.modules.biz.service.organ.repo.SocialorganRepository;
import com.xyz.modules.system.util.DictEnum;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 描述:
 *
 * @author 大豫竹
 * @create 2020-04-29 18:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockOrgan extends MockBase {
    @Resource
    private NonpublicRepository nonpublicRepository;
    @Resource
    private SocialorganRepository socialorganRepository;

    @Test
    public void init() {
        getNonpublic();
        getSocialorgan();
    }

    @Test
    public void getNonpublic() {
        userList.parallelStream().forEach(i -> {
            Nonpublic nonpublic = new Nonpublic();
            nonpublic.setNonId(UUID.randomUUID().toString());
            nonpublic.setCreditCode(getRandomCode(12));
            nonpublic.setEntityName(mockNameCn(4) + "有限责任公司");
            nonpublic.setEntityType(getRandomDictValue(DictEnum.QYLB.getDictId()));
            nonpublic.setEntityAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            nonpublic.setEntityAddr(getRandomAddrDetail());
            nonpublic.setEntityPhone(mockPhone());
            nonpublic.setEntityNum(RandomUtils.nextInt(1, 100));
            nonpublic.setLegalcardType(getRandomDictValue(DictEnum.ZJDM.getDictId()));
            nonpublic.setLegalcardCode(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            nonpublic.setLegalpersonName(mockNameCn(2));
            nonpublic.setLegalpersonMobile(mockPhone());
            nonpublic.setSupervisorName(mockNameCn(2));
            nonpublic.setSupervisorMobile(mockPhone());
            nonpublic.setIfDangerous(RandomUtils.nextInt(0, 2));
            nonpublic.setSafetroubleType(getRandomDictValue(DictEnum.AQYHLX.getDictId()));
            nonpublic.setAttention(getRandomDictValue(DictEnum.GZCD.getDictId()));
            nonpublic.setIfCondition(RandomUtils.nextInt(0, 2));
            nonpublic.setIfOrgan(RandomUtils.nextInt(0, 2));
            nonpublic.setOrganNum(RandomUtils.nextInt(0, 100));
            nonpublic.setIfUnion(RandomUtils.nextInt(0, 2));
            nonpublic.setUnionNum(RandomUtils.nextInt(0, 100));
            nonpublic.setIfYouthleague(RandomUtils.nextInt(0, 2));
            nonpublic.setYouthleagueNum(RandomUtils.nextInt(0, 1000));
            nonpublic.setIfWomenfeder(RandomUtils.nextInt(0, 2));
            nonpublic.setWomenfederNum(RandomUtils.nextInt(0, 100));
            nonpublic.setLng(RandomUtils.nextDouble(73, 135));
            nonpublic.setLat(RandomUtils.nextDouble(3, 53));
            nonpublic.setEffDate(getTime());
            nonpublic.setExpDate(getTime());
            nonpublic.setStatus("1");
            nonpublic.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            nonpublic.setOperName(i.getId());
            nonpublic.setOperDate(getTime());
            nonpublic.setCreator(i.getId());
            nonpublic.setCreateTime(getTime());
            nonpublic.setUnitCode(i.getDept().getCode());
            nonpublicRepository.save(nonpublic);
        });
    }
    @Test
    public void getSocialorgan() {
        userList.parallelStream().forEach(i -> {
            Socialorgan socialorgan = new Socialorgan();
            socialorgan.setSociaId(UUID.randomUUID().toString());
            socialorgan.setCreditCode(getRandomCode(12));
            socialorgan.setRegisCode(getRandomCode(12));
            socialorgan.setSocialorganName(mockNameCn(4) + "社会组织");
            socialorgan.setRegisunitCode(getRandomCode(6));
            socialorgan.setRegisunitName(i.getDept().getName());
            socialorgan.setAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            socialorgan.setAddr(getRandomAddrDetail());
            socialorgan.setApprDate(getTime());
            socialorgan.setSocialorganType(getRandomDictValue(DictEnum.SHZZLX.getDictId()));
            socialorgan.setScoiStatus("1");
            socialorgan.setLegalcardType(getRandomDictValue(DictEnum.ZJDM.getDictId()));
            socialorgan.setLegalcardCode(getIdNo(RandomUtils.nextBoolean()).substring(0, 18));
            socialorgan.setLegalpersonName(mockNameCn(2));
            socialorgan.setLegalpersonMobile(mockPhone());
            socialorgan.setWorkAddrcode(getRandomDictValue(DictEnum.ADDRESS.getDictId()));
            socialorgan.setWorkAddr(getRandomAddrDetail());
            socialorgan.setSupervisorName(mockNameCn(2));
            socialorgan.setSupervisorMobile(mockPhone());
            socialorgan.setAttention(getRandomDictValue(DictEnum.GZCD.getDictId()));
            socialorgan.setIfCondition(RandomUtils.nextInt(0, 2));
            socialorgan.setIfOrgan(RandomUtils.nextInt(0, 2));
            socialorgan.setOrganNum(RandomUtils.nextInt(0, 111));
            socialorgan.setIfUnion(RandomUtils.nextInt(0, 2));
            socialorgan.setUnionNum(RandomUtils.nextInt(0, 111));
            socialorgan.setIfYouthleague(RandomUtils.nextInt(0, 2));
            socialorgan.setYouthleagueNum(RandomUtils.nextInt(0, 111));
            socialorgan.setIfWomenfeder(RandomUtils.nextInt(0, 2));
            socialorgan.setWomenfederNum(RandomUtils.nextInt(0, 111));
            socialorgan.setFundingSource(mockRandomZh_cn());
            socialorgan.setIfOverseabg(RandomUtils.nextInt(0, 2));
            socialorgan.setLng(RandomUtils.nextDouble(73, 135));
            socialorgan.setLat(RandomUtils.nextDouble(3, 53));
            socialorgan.setEffDate(getTime());
            socialorgan.setExpDate(getTime());
            socialorgan.setStatus("1");
            socialorgan.setStatusCd(getRandomDictValue(DictEnum.SJZT.getDictId()));
            socialorgan.setOperName(i.getId());
            socialorgan.setOperDate(getTime());
            socialorgan.setCreator(i.getId());
            socialorgan.setCreateTime(getTime());
            socialorgan.setUnitCode(i.getDept().getCode());
            socialorganRepository.save(socialorgan);
        });
    }
}