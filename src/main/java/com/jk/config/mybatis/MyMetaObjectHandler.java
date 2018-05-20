package com.jk.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @packageName: com.jk.config.mybatis
 * @description: 自定义填充公共字段
 * @author: cuiP
 * @date: 2018/4/25 16:46
 * @version: V1.0.0
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        metaObject.setValue("createTime", now);
        metaObject.setValue("modifyTime", now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("modifyTime", LocalDateTime.now());
    }
}
