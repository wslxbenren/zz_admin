package com.xyz.modules.biz.audit.mongo.config;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.alibaba.fastjson.JSON;
import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.audit.mongo.service.ModifyRecordsRepo;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据更新监听器
 *
 * @author xiaonanfeng
 * @date 2020-04-15
 */
//@Component
public class DataUpdateEventListener implements PostUpdateEventListener {

    private static final long serialVersionUID = 1L;

    public static final DataUpdateEventListener INSTANCE = new DataUpdateEventListener();

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private ModifyRecordsRepo modifyRecordsRepo;

    @Deprecated
    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        //实体类名称
        String entityName = event.getEntity().getClass().getSimpleName();
        //实体类ID
        String entityId = event.getId().toString();
        //所有属性长度
        int len = 0;
        Object[] now = event.getState();
        Object[] old = event.getOldState();
        len = old.length;
        //判断对象是否发生前后不一致
        boolean hasChange = now.equals(old);
        List<ModifyRecords> modifyRecordsList = new ArrayList<>();
        try {
            //如果确实发生改变
            if (!hasChange) {
                //遍历对象属性
                for (int i = 0; i < len; i++) {
                    //当前字段
                    String popName = event.getPersister().getPropertyNames()[i];
                    //if (!popName.) {
                    //}
                    //旧值
                    Object oldValue = old[i];
                    // 更新后的新值
                    Object newValue = now[i];
                    // 发生改变的属性
                    if (!oldValue.equals(newValue)) {
                        ModifyRecords modifyRecords = new ModifyRecords();
                        modifyRecords.setEntityName(entityName);
                        modifyRecords.setEntityId(entityId);
                        modifyRecords.setModifyUserid("user");
                        modifyRecords.setModifyTime(LocalDateTime.now());
                        modifyRecords.setModifyContent(popName);
                        modifyRecords.setBefore(oldValue.toString());
                        modifyRecords.setAfter(newValue.toString());
                        modifyRecordsList.add(modifyRecords);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(JSON.toJSONString(modifyRecordsList));
            System.out.println(1);
        }
    }
}
