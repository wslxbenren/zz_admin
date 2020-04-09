package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.service.DictService;
import com.xyz.modules.system.service.dto.DictDTO;
import com.xyz.modules.system.service.dto.DictQueryCriteria;
import com.xyz.modules.system.service.mapper.DictMapper;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.*;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Object queryAll(DictQueryCriteria dict, Pageable pageable){
        Page<Dict> page = dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb), pageable);
        return PageUtil.toPage(page.map(dictMapper::toDto));
    }

    @Override
    public DictDTO findById(Long id) {
        Optional<Dict> dict = dictRepository.findById(id);
        ValidationUtil.isNull(dict,"Dict","id",id);
        return dictMapper.toDto(dict.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDTO create(Dict resources) {
        return dictMapper.toDto(dictRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dict resources) {
        Optional<Dict> optionalDict = dictRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDict,"Dict","id",resources.getId());
        Dict dict = optionalDict.get();
        resources.setId(dict.getId());
        dictRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictRepository.deleteById(id);
    }

    /**
     * 方法暂时作废， 后面可能还会启用
     * @param className 指定类全限定名
     * @return
     */
    @Deprecated
    public List<Dict> buildDict(String className) {
        List<String> dsf = new ArrayList<String>();
        try {
            Field[] fields = Class.forName(className).getDeclaredFields();
            for (Field field : fields) {
                com.xyz.modules.system.util.annotation.Dict d = field.getAnnotation(com.xyz.modules.system.util.annotation.Dict.class);
                if(d != null) {
                    dsf.add(d.value().getDistName());
                }
            }

            List<Dict> res =  dictRepository.findAll((root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(root.<String> get("name").in(dsf));
                query.where(predicates.toArray(new Predicate[0]));
                return cb.and(predicates.toArray(new Predicate[0]));
            });
            Iterator iterator = res.iterator();
            while (iterator.hasNext()) {
                Dict tm = (Dict)iterator.next();
                if(tm.getDictDetails().size() > 100) {
                    tm.setDictDetails(Collections.emptyList());
                }
            }
            return res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Dict> get2LevelDict() {
        return dictRepository.findAll();
    }
}