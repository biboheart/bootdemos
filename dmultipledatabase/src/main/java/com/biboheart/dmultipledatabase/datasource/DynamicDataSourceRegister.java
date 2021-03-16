package com.biboheart.dmultipledatabase.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 注册动态数据源
 * 初始化数据源和提供了执行动态切换数据源的工具类
 * EnvironmentAware（获取配置文件配置的属性值）
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    //指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    //默认数据源
    private DataSource defaultDataSource;

    //用户自定义数据源
    private Map<String, DataSource> extendDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initExtendDataSources(environment);
    }

    private void initDefaultDataSource(Environment environment) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver-class-name", environment.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url", environment.getProperty("spring.datasource.url"));
        dsMap.put("username", environment.getProperty("spring.datasource.username"));
        dsMap.put("password", environment.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(dsMap);
    }

    private void initExtendDataSources(Environment environment) {
        // 读取配置文件获取更多数据源
        String dsPrefixs = environment.getProperty("extend.names");
        if (null == dsPrefixs) {
            return;
        }
        for (String dsPrefix : dsPrefixs.split(",")) {
            // 多个数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver-class-name", environment.getProperty("extend." + dsPrefix + ".datasource.driver-class-name"));
            dsMap.put("url", environment.getProperty("extend." + dsPrefix + ".datasource.url"));
            dsMap.put("username", environment.getProperty("extend." + dsPrefix + ".datasource.username"));
            dsMap.put("password", environment.getProperty("extend." + dsPrefix + ".datasource.password"));
            DataSource ds = buildDataSource(dsMap);
            extendDataSources.put(dsPrefix, ds);
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 添加默认数据源
        targetDataSources.put("dataSource", this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加其他数据源
        if (!extendDataSources.isEmpty()) {
            targetDataSources.putAll(extendDataSources);
            DynamicDataSourceContextHolder.dataSourceIds.addAll(extendDataSources.keySet());
        }
        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        registry.registerBeanDefinition("dataSource", beanDefinition);
        log.info("Dynamic DataSource Registry");
    }

    private DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver-class-name").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
