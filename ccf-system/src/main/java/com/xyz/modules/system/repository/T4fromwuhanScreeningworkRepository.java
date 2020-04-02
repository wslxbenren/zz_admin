package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.T4fromwuhanScreeningwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author dadovicn
* @date 2020-02-02
*/
public interface T4fromwuhanScreeningworkRepository extends JpaRepository<T4fromwuhanScreeningwork, String>, JpaSpecificationExecutor {

    @Query("select t4.deptName as dept,count(1) as num from T4fromwuhanScreeningwork t4 GROUP BY t4.deptName")
    List selectGropuBy();
}