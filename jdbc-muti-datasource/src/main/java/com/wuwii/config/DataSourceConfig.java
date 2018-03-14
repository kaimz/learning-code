package com.wuwii.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JDBC 多数据源配置
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/14 18:16</pre>
 */
@Configuration
public class DataSourceConfig {

    /**
     * 注册 data source
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean("firstDataSource")
    @Primary // 有相同实例优先选择
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.second-datasource")
    @Bean("secondDataSource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("firstJdbcTemplate")
    @Primary
    public JdbcTemplate firstJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
