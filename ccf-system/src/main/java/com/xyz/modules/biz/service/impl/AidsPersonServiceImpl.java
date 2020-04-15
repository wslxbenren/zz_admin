package com.xyz.modules.biz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.AidsPerson;
import com.xyz.modules.biz.repository.AidsPersonRepository;
import com.xyz.modules.biz.service.AidsPersonService;
import com.xyz.modules.biz.service.dto.AidsPersonDTO;
import com.xyz.modules.biz.service.dto.AidsPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.AidsPersonMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AidsPersonServiceImpl implements AidsPersonService {

    @Autowired
    private AidsPersonRepository AidsPersonRepository;

    @Resource
    private AidsPersonMapper AidsPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;


    @Override
    @Transactional
    public Object queryAll(AidsPersonQueryCriteria criteria, Pageable pageable) {
        log.info("实现条件查询列表-分页");
        Page<AidsPerson> page = AidsPersonRepository.findAll(audit.genSpecification(criteria), pageable);
        List<AidsPersonDTO> aidsPersonDTOS = AidsPersonMapper.toDto(page.getContent());
        for (AidsPersonDTO f : aidsPersonDTOS) {
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), f.getEduLevel());
            f.setEduLevelStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.AJLB.getDistName(), f.getCaseType());
            f.setCaseTypeStr(dd == null ? "无数据" : dd);

            dd = dictDetailService.transDict(DictEnum.GRTJ.getDistName(), f.getRoutesInfection());
            f.setRoutesInfectionStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.GZLX.getDistName(), f.getTakeType());
            f.setTakeTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.BFQK.getDistName(), f.getHelpComments());
            f.setHelpCommentsStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.SZQK.getDistName(), f.getDetainType());
            f.setDetainTypeStr(dd == null ? "无数据" : dd);

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", aidsPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(AidsPersonQueryCriteria criteria) {
        log.info("实现条件查询AidsPerson 列表 ");
        return AidsPersonMapper.toDto(AidsPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public AidsPersonDTO findById(String aidsId) {
        log.info("查询AidsPerson详情 ");
        Optional<AidsPerson> AidsPerson = AidsPersonRepository.findById(aidsId);
        ValidationUtil.isNull(AidsPerson, "AidsPerson", "aidsId", aidsId);
        return AidsPersonMapper.toDto(AidsPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AidsPersonDTO create(AidsPerson resources) {
        log.info("新增 AidsPerson");
        resources.setAidsId(IdUtil.simpleUUID());
        if (AidsPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null) {
            log.info("AidsPerson IdentityNum 重复 ");
            throw new EntityExistException(AidsPerson.class, "identity_num", resources.getIdentityNum());
        }
        return AidsPersonMapper.toDto(AidsPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AidsPerson resources) {
        log.info("修改 AidsPerson");
        Optional<AidsPerson> optionalAidsPerson = AidsPersonRepository.findById(resources.getAidsId());
        ValidationUtil.isNull(optionalAidsPerson, "AidsPerson", "id", resources.getAidsId());
        AidsPerson AidsPerson = optionalAidsPerson.get();
//        AidsPerson1 = AidsPersonRepository.findByIdentityNum(resources.getIdentityNum());
//        if(AidsPerson1 != null && !AidsPerson1.getAidsId().equals(AidsPerson.getAidsId())){
//            throw new EntityExistException(AidsPerson.class,"identity_num",resources.getIdentityNum());
//        }
        AidsPerson.copy(resources);
        AidsPersonRepository.save(AidsPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String aidsId) {
        log.info("删除 AidsPerson");
        AidsPersonRepository.deleteById(aidsId);
    }

    @Override
    public void export(HttpServletResponse response, AidsPersonQueryCriteria criteria) throws IOException {
        OutputStream out = response.getOutputStream();
        response.setHeader("content-disposition",
                "attachment;filename=" + URLEncoder.encode(System.currentTimeMillis() + ".xls", "utf-8"));
        //在未知文件类型时需要使用二进制流的方式下载文件
        response.setContentType("application/octet-stream");
        String[] titles = {"id", "生日", "订单生成时间", "任务id"};
        //创建一个POI的Excel工作簿
        Workbook wb = new XSSFWorkbook();
        //在工作簿中创建一个表格
        Sheet sheet1 = wb.createSheet("sheet1");
        int rowCount = 0;
        //创建表格中的行，方法传参需要当前行数
        Row row = sheet1.createRow(rowCount);
        rowCount++;
        Cell cell = null;
        //为每行添加数据
        for (int i = 0; i < titles.length; i++) {
            //在此行中获取一个单元格
            cell = row.createCell(i);
            //写入单元格内容
            cell.setCellValue(titles[i]);
        }
        // 查询结果集
        List<AidsPerson> list = (List<AidsPerson>) AidsPersonRepository.findAll(audit.genSpecification(criteria));
        if (list != null && !list.isEmpty()) {
            List<AidsPersonDTO> aidsPersonDTOS = AidsPersonMapper.toDto(list);
            for (AidsPersonDTO vo : aidsPersonDTOS) {
                row = sheet1.createRow(rowCount);
                rowCount++;

                cell = row.createCell(0);
                String orderNo = vo.getAidsId();
                cell.setCellValue(orderNo == null ? "/" : orderNo);

                cell = row.createCell(1);
                cell.setCellValue(vo.getDateBirth().toString());


                cell = row.createCell(2);
                String createTime =  vo.getCreateTime().toString();
                cell.setCellValue(  createTime );

                cell = row.createCell(3);
                cell.setCellValue(vo.getOperName() == null ? "/" : vo.getOperName());
            }
        }
        try {
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}