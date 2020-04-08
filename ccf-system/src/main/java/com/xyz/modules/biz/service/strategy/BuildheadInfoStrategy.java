package com.xyz.modules.biz.service.strategy;

import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.service.dto.DictDTO;
import com.xyz.modules.system.service.mapper.DictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dadovicn
 */
@Service(value = "BuildheadInfoController.class")
public class BuildheadInfoStrategy implements DictStrategy{
    private static final Logger log = LoggerFactory.getLogger(BuildheadInfoStrategy.class);

    private final DictRepository dictRepository;

    private final DictMapper dictMapper;

    public BuildheadInfoStrategy(DictMapper dictMapper, DictRepository dictRepository) {
        this.dictMapper = dictMapper;
        this.dictRepository = dictRepository;
    }

    @Override
    public List<DictDTO> buildDict() {
        List<String> dsf = new ArrayList<String>();
        try {
            Field[] fields = Class.forName("com.xyz.modules.biz.domain.BuildheadInfo").getDeclaredFields();
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
            return dictMapper.toDto(res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
