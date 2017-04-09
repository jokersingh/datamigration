/**
 * 
 */
package com.barun.datamigration.common;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.barun.datamigration.model.Configuration;

import oracle.jdbc.pool.OracleDataSource;

/**
 * @author bibhu
 *
 */
public final class JdbcUtil {

	@Bean
	public static DataSource getOracleDS(Configuration configuration) throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL(configuration.getOracle().getUrl());
		dataSource.setUser(configuration.getOracle().getUserName());
		dataSource.setPassword(configuration.getOracle().getPassword());
		return dataSource;
	}
	
	@Bean
	public static DataSource getMySqlDS(Configuration configuration) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(configuration.getMySql().getDriverName());
		dataSource.setUrl(configuration.getMySql().getUrl());
		dataSource.setUsername(configuration.getMySql().getUserName());
		dataSource.setPassword(configuration.getMySql().getPassword());
		return dataSource;
	}
}
