package com.xyz.modules.biz.audit.mongo.config;

import java.util.Collections;
import java.util.Map;

import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

/**
 * Hibernate和JPA的用户自定义配置
 * 注册自定义数据处理类MetadataExtractorIntegrator
 * @author xiaonanfeng
 * @date 2020-04-15
 */
@Component
public class JpaCallHibernateConfig implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(MetadataExtractorIntegrator.INSTANCE));
    }
}
