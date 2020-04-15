package com.xyz.modules.biz.config;

import com.xyz.modules.biz.listener.DataUpdateEventListener;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * 注册Hibernate数据更新监听器
 * @author xiaonanfeng
 * @date 2020-04-15
 */
public class MetadataExtractorIntegrator implements Integrator {

    public static final MetadataExtractorIntegrator INSTANCE = new MetadataExtractorIntegrator();

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventListenerRegistry.appendListeners(EventType.POST_UPDATE, DataUpdateEventListener.INSTANCE);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

    }
}
