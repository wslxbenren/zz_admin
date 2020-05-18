package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.service.CompareFieldsService;
import com.xyz.modules.system.util.annotation.Dict;
import com.xyz.modules.system.util.annotation.FieldAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author lx
 * @date 2020/5/14 15:58
 * <p>
 * 比较对象属性差异，并返回差异的属性集合
 */
@Service
public class CompareFieldsServiceImpl implements CompareFieldsService  {
    @Autowired
    private  DictDetailRepository dictDetailRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public  String compareModifyRecords(Object obj1, Object obj2, String[] ignoreArr) {
        StringBuffer sb = new StringBuffer();
        Map<String, List<Object>> resultMap = compareFields(obj1, obj2, ignoreArr);
        Set<String> strings = resultMap.keySet();
        for (String s : strings) {
            List<Object> list = resultMap.get(s);
            sb.append("「" + s + "」由【" + list.get(0) + "】  改为 【" + list.get(1) + "】,");

        }
        return sb.toString().length()==0?"": sb.toString().substring(0,sb.length()-1);
    }

    /**
     * @param obj1
     * @param obj2
     * @param ignoreArr 不做比较的属性集
     * @return
     */
    public  Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] ignoreArr) {
        Map<String, List<Object>> map = new HashMap();
        List<String> ignoreList = null;
        if (ignoreArr != null && ignoreArr.length > 0) {
            // array转化为list
            ignoreList = Arrays.asList(ignoreArr);
        }
        if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性
            Map<String, String> resultMap = new HashMap<String, String>();
            List<Field> fields = new ArrayList<>();
            // 获取类字段，和值
            fields.addAll(Arrays.asList(obj1.getClass().getDeclaredFields()));
            try {
                for (Field field : fields) { // 这里就是所有的属性了
                    //抓所有的私有属性
                    if (Modifier.isPrivate(field.getModifiers())) {
                        String name = field.getName(); // 属性名
                        if (ignoreList != null && ignoreList.contains(name)) {// 如果当前属性选择忽略比较，跳到下一次循环
                            continue;
                        }
                        // 设置允许访问私有类
                        field.setAccessible(true);
                        // 格式化请求参数字段
                        Object o1 = field.get(obj1);
                        Object o2 = field.get(obj2);


                       /* if (o1 instanceof Timestamp) {
                            o1 = new Date(((Timestamp) o1).getTime());
                        }
                        if (o2 instanceof Timestamp) {
                            o2 = new Date(((Timestamp) o2).getTime());
                        }*/

                        if (o1 == null && o2 == null) {
                            continue;
                        }

                        // 属性名和接口需要的参数名不一样，需要用注解里的名字
                        FieldAlias anno = field.getAnnotation(FieldAlias.class);
                        Dict dict = field.getAnnotation(Dict.class);

                        if (o1 == null && o2 != null && anno != null || o1 != null && o2 == null && anno != null) {
                            List<Object> list = new ArrayList<Object>();

                            if (dict != null){
                                list.add(getDictLabels(dict, o1));
                                list.add(getDictLabels(dict, o2));
                                map.put(anno.value(), list);
                            }else {
                                if (anno.value().equals("所属单位")){
                                    String nameByCode = deptRepository.findNameByCode(o1.toString());
                                    list.add(nameByCode);
                                    nameByCode = deptRepository.findNameByCode(o2.toString());
                                    list.add(nameByCode);
                                }else {
                                    list.add(o1);
                                    list.add(o2);
                                }
                                map.put(anno.value(), list);
                            }

                            continue;
                        }

                        if (!o1.equals(o2) && anno != null ) {// 比较这两个值是否相等,不等就可以放入map了
                            List<Object> list = new ArrayList<Object>();
                            if (dict != null){
                                list.add(getDictLabels(dict, o1));
                                list.add(getDictLabels(dict, o2));
                                map.put(anno.value(), list);
                            }else {
                                if (anno.value().equals("所属单位")){
                                    String nameByCode = deptRepository.findNameByCode(o1.toString());
                                    list.add(nameByCode);
                                    nameByCode = deptRepository.findNameByCode(o2.toString());
                                    list.add(nameByCode);
                                }else {
                                    list.add(o1);
                                    list.add(o2);
                                }
                                map.put(anno.value(), list);
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        return map;
    }

    public  String getDictLabels(Dict dict,Object values){
        if (values == null){
            return null;
        }
        if (dict.value().getDictId() == 0){
           return values.toString().equals("0") ? "否" : "是";
        }else if (dict.multiple() == true){//多选
            String[] valueArr = values.toString().split(",");
            String labelByValues = dictDetailRepository.getLabelByValues(dict.value().getDictId(), Arrays.asList(valueArr));
            return labelByValues;
        }else if (dict.level() == true){//多级
            String addrParentStr = dictDetailRepository.getAddrParentStr(dict.value().getDictId(), values.toString());
            return addrParentStr;
        } else {
            return dictDetailRepository.findByDictIdAndValue(dict.value().getDictId(),values);
        }
    }


}