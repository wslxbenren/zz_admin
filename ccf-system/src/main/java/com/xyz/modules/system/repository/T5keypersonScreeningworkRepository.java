package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.T5keypersonScreeningwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-02-02
*/
public interface T5keypersonScreeningworkRepository extends JpaRepository<T5keypersonScreeningwork, String>, JpaSpecificationExecutor {
}