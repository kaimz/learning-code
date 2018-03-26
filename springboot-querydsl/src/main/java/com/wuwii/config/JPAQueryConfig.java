package com.wuwii.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/26 18:52</pre>
 */
@Configuration
public class JPAQueryConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
