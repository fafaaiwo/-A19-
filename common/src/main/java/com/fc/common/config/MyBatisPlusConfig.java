package com.fc.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author lzr
 * @date 2021/3/18 16:03
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            //自动添加
            @Override
            public void insertFill(MetaObject metaObject) {
                //第二参数要和实体类中字段名一致，第三个参数字段类型要和实体类中字段类型一致，最后一个参数是待填入的数据
                setFieldValByName( "gmtCreate", new Date(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                setFieldValByName( "gmtUpdate", new Date(), metaObject);
            }
        };
    }
}
