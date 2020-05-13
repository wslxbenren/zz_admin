package com.xyz.modules.biz.audit.mongo.service;

import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * mongo 操作
 * @author ccf0004
 */
public interface ModifyRecordsRepo extends MongoRepository<ModifyRecords, String> {

}
