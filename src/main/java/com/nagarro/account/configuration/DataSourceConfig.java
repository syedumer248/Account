package com.nagarro.account.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
	/*
	 * @Bean public DataSource getDataSource() { DataSourceBuilder dataSourceBuilder
	 * = DataSourceBuilder.create();
	 * dataSourceBuilder.driverClassName("net.ucanaccess.jdbc.UcanaccessDriver");
	 * dataSourceBuilder.url("jdbc:ucanaccess://e:/projects/accountsdb.accdb");
	 * return dataSourceBuilder.build(); }
	 */
}
