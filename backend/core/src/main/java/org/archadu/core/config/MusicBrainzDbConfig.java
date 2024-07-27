package org.archadu.core.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.archadu.core.fm",
        entityManagerFactoryRef = "mbEntityManagerFactory",
        transactionManagerRef = "mbTransactionManager"
)
public class MusicBrainzDbConfig {

    @Bean(name = "mbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mb")
    public DataSource mbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.archadu.core.fm.model")
                .persistenceUnit("mb")
                .build();
    }

    @Bean(name = "mbTransactionManager")
    public PlatformTransactionManager mbTransactionManager(
            @Qualifier("mbEntityManagerFactory") EntityManagerFactory mbEntityManagerFactory) {
        return new JpaTransactionManager(mbEntityManagerFactory);
    }
}
