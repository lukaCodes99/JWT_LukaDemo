package com.lukam.demosJWT.user;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.lukam.demosJWT.repo.user",
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "getUserPTM"
)
public class UserDataSource {


    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    DataSourceProperties getDataSourceProperties(){
        return new DataSourceProperties();
    }
    
    @Primary
    @Bean
    @ConfigurationProperties("user.datasource.configuration")
    DataSource getDatasource(){
        return getDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build(); //10:55 to check if another thing needs to be done
    }

    
    @Bean(name = "userEntityManagerFactory")
    @Primary
    LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder){
        return builder.dataSource(getDatasource()).packages("com.lukam.demosJWT.user").build();
    }

    @Bean
    @Primary
    PlatformTransactionManager getUserPTM(
        final @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean
    ){
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }
    
    
}
