package com.jiwoong.billingsystem.global.config.conn;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.jiwoong.billingsystem.streaming",
        entityManagerFactoryRef = "streamingEntityManagerFactory",
        transactionManagerRef = "streamingTransactionManager"
)
@EntityScan(
        basePackages = "com.jiwoong.billingsystem.streaming.entity"
)
public class StreamingDataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean streamingEntityManagerFactory(
            @Qualifier("streamingDataSource") DataSource streamingDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(streamingDataSource);
        em.setPackagesToScan("com.jiwoong.billingsystem.streaming.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public JpaTransactionManager streamingTransactionManager(
            @Qualifier("streamingEntityManagerFactory") EntityManagerFactory streamingEntityManagerFactory) {
        return new JpaTransactionManager(streamingEntityManagerFactory);
    }
}
