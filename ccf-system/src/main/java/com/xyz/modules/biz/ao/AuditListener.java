package com.xyz.modules.biz.ao;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 历史记录监听
 */
@Slf4j
public class AuditListener {
    @PrePersist
    <T> void onPrePersist(T entity) {
        // mongoService.save(className: {entity}
        log.info("{} -----> onPrePersist():", entity.getClass().getSimpleName());
    }

    @PostPersist
    <T> void onPostPersist(T entity) {
        log.info("{} -----> onPostPersist():", entity.getClass().getSimpleName());
    }

    @PostLoad
    <T> void onPostLoad(T entity) {
        log.info("{} -----> onPostLoad():", entity.getClass().getSimpleName());
    }

    @PreUpdate
    <T> void onPreUpdate(T entity) {
        log.info("{} -----> onPreUpdate():", entity.getClass().getSimpleName());
    }

    @PostUpdate
    <T> void onPostUpdate(T entity) {
        // mongo
        log.info("{} -----> onPostUpdate():", entity.getClass().getSimpleName());
    }

    @PreRemove
    <T> void onPreRemove(T entity) {
        log.info("{} -----> onPreRemove():", entity.getClass().getSimpleName());
    }

    @PostRemove
    <T> void onPostRemove(T entity) {
        log.info("{} -----> onPostRemove():", entity.getClass().getSimpleName());
    }
}
