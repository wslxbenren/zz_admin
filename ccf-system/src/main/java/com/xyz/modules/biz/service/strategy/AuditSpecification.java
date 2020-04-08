package com.xyz.modules.biz.service.strategy;

import com.xyz.utils.QueryHelp;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 * 这里暂定认为同级同部门之间不可互相查看
 */
public class AuditSpecification {

    private static final String CREATOR = "creator";

    public static <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Predicate dd = QueryHelp.getPredicate(root,q,criteriaBuilder);
            predicateList.add(dd);
            try {
                Predicate cc = criteriaBuilder.equal(root.get(CREATOR).as(String.class), q.getClass().getField(CREATOR).get(q));
                predicateList.add(cc);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return criteriaBuilder.or((Predicate[])predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

}
