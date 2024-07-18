package com.jiwoong.billingsystem.global.config.conn;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.jiwoong.billingsystem.adjustment.repository",
        entityManagerFactoryRef = "batchEntityManagerFactory",
        transactionManagerRef = "batchTransactionManager"
)
@EntityScan(
        basePackages = "com.jiwoong.billingsystem.adjustment.entity"
)
public class BatchDataSourceConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(
            @Qualifier("batchDataSource") DataSource batchDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(batchDataSource);
        em.setPackagesToScan("com.jiwoong.billingsystem.adjustment.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public JpaTransactionManager batchTransactionManager(
            @Qualifier("batchEntityManagerFactory") EntityManagerFactory batchEntityManagerFactory) {
        return new JpaTransactionManager(batchEntityManagerFactory);
    }
}

