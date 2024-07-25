package org.archadu.core.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.archadu.core.repository",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class CoreDbConfig {
    @Primary
    @Bean(name = "coreDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.core")
        public DataSource primaryDataSource() {
            return DataSourceBuilder.create().build();
        }
@Primary
        @Bean(name = "primaryEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
                EntityManagerFactoryBuilder builder,
                @Qualifier("coreDataSource") DataSource dataSource) {
            return builder
                    .dataSource(dataSource)
                    .packages("org.archadu.core")
                    .persistenceUnit("primary")
                    .build();
        }
@Primary
        @Bean(name = "primaryTransactionManager")
        public PlatformTransactionManager primaryTransactionManager(
                @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
            return new JpaTransactionManager(primaryEntityManagerFactory);
        }

}
