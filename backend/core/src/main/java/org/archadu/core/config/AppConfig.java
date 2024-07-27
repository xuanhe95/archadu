package org.archadu.core.config;

import com.zaxxer.hikari.HikariDataSource;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

//    @Bean(name = "coreData")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSourceProperties coreDataSourceProperties() {
//        return new DataSourceProperties();
//    }

//    @Bean(name = "mbData")
//    @ConfigurationProperties(prefix = "spring.mb")
//    public DataSourceProperties musicDataSourceProperties() {
//        return new DataSourceProperties();
//    }

//    @Bean(name = "mbDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mb.hikari")
//    public DataSource musicDataSource() {
//        return musicDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }

//    @Primary
//    @Bean(name = "coreDataSource2")
//    @ConfigurationProperties(prefix = "spring.datasource.core.hikari")
//    public DataSource dataSource() {
//        return coreDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//

//    @Bean(name = "mbJdbcTemplate")
//    public JdbcTemplate musicJdbcTemplate(@Qualifier("mbDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean(name = "coreJdbcTemplate")
        public JdbcTemplate coreJdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


   @Bean
    public CoverArtArchiveClient coverArtArchiveClient() {
        return new DefaultCoverArtArchiveClient();
    }





}
