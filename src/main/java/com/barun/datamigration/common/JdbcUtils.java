/**
 * 
 */
package com.barun.datamigration.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.yaml.snakeyaml.Yaml;

import com.barun.datamigration.model.Configuration;

import oracle.jdbc.pool.OracleDataSource;

/**
 * @author bibhu
 *
 */
public final class JdbcUtils {

	@Bean
	public static Configuration loadConfiguration(){
		Yaml yaml = new Yaml();
		Configuration conf = null;
		try {
			conf = yaml.loadAs(new FileInputStream(new File("database.yml")), Configuration.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return conf;
	}
	@Bean
	public static DataSource getOracleDS() throws SQLException {
		Configuration configuration = loadConfiguration();
		//OracleDataSource dataSource = new OracleDataSource();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(configuration.getOracle().getDriverName());
		dataSource.setUrl(configuration.getOracle().getUrl());
		dataSource.setUsername(configuration.getOracle().getUserName());
		dataSource.setPassword(configuration.getOracle().getPassword());
		return dataSource;
	}
	
	@Bean
	public static DataSource getMySqlDS() throws SQLException{
		Configuration configuration = loadConfiguration();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(configuration.getMySql().getDriverName());
		dataSource.setUrl(configuration.getMySql().getUrl());
		dataSource.setUsername(configuration.getMySql().getUserName());
		dataSource.setPassword(configuration.getMySql().getPassword());
		return dataSource;
	}
	
	/**
	 * @param mysqlDS
	 * @param tableName 
	 * @throws SQLException
	 */
	public static void tableCleanUp(DataSource mysqlDS, String tableName) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		conn.createStatement().executeUpdate("TRUNCATE TABLE "+tableName);
		conn.close();
	}
}
