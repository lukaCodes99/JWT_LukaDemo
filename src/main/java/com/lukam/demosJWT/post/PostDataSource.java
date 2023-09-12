package com.lukam.demosJWT.post;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.lukam.demosJWT.repo.post",
    entityManagerFactoryRef = "postEntityManagerFactory",
    transactionManagerRef = "getPostPTM"
)
public class PostDataSource {


    @Bean
    @ConfigurationProperties(prefix = "post.datasource")
    DataSourceProperties getDataSourceProperty(){
        return new DataSourceProperties();
    }
    
    @Bean
    @ConfigurationProperties("post.datasource.configuration")
    DataSource getDataSource(){
        return getDataSourceProperty().initializeDataSourceBuilder().type(HikariDataSource.class).build(); //10:55 to check if another thing needs to be done
    }

    
    @Bean(name = "postEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder){
        return builder.dataSource(getDataSource()).packages("com.lukam.demosJWT.post").build();
    }

    @Bean
    PlatformTransactionManager getPostPTM(
        final @Qualifier("postEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean
    ){
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }
    
    
}
