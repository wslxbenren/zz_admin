package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
public interface DeptRepository extends JpaRepository<Dept, String>, JpaSpecificationExecutor {

    /**
     * findByPid
     * @param id
     * @return
     */
    List<Dept> findByPid(String id);

    @Query(value = "select name from dept where id = ?1",nativeQuery = true)
    String findNameById(String id);

    @Query(value = "select name from dept where code = ?1",nativeQuery = true)
    String findNameByCode(String code);

    Set<Dept> findByRoles_Id(Long id);

    @Query(value = "select getDeptChildList(?1)", nativeQuery = true)
    String getDeptDownGradeCodes(String code);

    @Query(value = "select grage from dept where code = ?1", nativeQuery = true)
    String getGradeByCode(String code);


    @Query(value = "select code from dept where id = ?1", nativeQuery = true)
    String findCodeById(String id);

    @Query(value = "select grage from dept where id = ?1", nativeQuery = true)
    Integer findGrageById(String id);
}