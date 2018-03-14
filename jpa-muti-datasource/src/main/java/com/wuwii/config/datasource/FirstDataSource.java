package com.wuwii.config.datasource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/3/14 23:28</pre>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
)
public class FirstDataSource {


}
