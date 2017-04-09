/**
 * 
 */
package com.barun.datamigration.boot;

import static org.easybatch.core.job.JobBuilder.aNewJob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.mapper.BeanIntrospectionException;
import org.easybatch.jdbc.BeanPropertiesPreparedStatementProvider;
import org.easybatch.jdbc.JdbcRecordReader;
import org.easybatch.jdbc.JdbcRecordWriter;
import org.yaml.snakeyaml.Yaml;

import com.barun.datamigration.common.JdbcUtil;
import com.barun.datamigration.common.QueryBuilder;
import com.barun.datamigration.listener.CustomJobListener;
import com.barun.datamigration.mapper.CustomRecordMapper;
import com.barun.datamigration.model.Article;
import com.barun.datamigration.model.Configuration;
import com.barun.datamigration.model.Table;

/**
 * @author bibhu
 *
 */
public class App {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, FileNotFoundException{
		
		Yaml yaml = new Yaml();
		@SuppressWarnings("unchecked")
		Map<String,List<Table>> tables  = yaml.loadAs(new FileInputStream(new File("src/main/resources/tables.yml")), Map.class);
		//Map tables  = yaml.loadAs(new FileInputStream(new File("src/main/resources/tables.yml")), Map.class);
		System.out.println(tables);
		Configuration conf = yaml.loadAs(new FileInputStream(new File("src/main/resources/database.yml")), Configuration.class);
		DataSource oracleDS = JdbcUtil.getOracleDS(conf);
		DataSource mysqlDS = JdbcUtil.getMySqlDS(conf);
		tableCleanUp(mysqlDS);
        String[] fields = {"ID","SUBJECT","BODY", "CREATED_ON"};
        // Build a batch job
		Job job = buildJob(oracleDS, mysqlDS, fields,"article",Article.class);
        // Execute the job
        JobExecutor jobExecutor = new JobExecutor();
        JobReport jobReport = jobExecutor.execute(job);
        jobExecutor.shutdown();
        System.out.println(jobReport);

	}

	/**
	 * @param mysqlDS
	 * @throws SQLException
	 */
	private static void tableCleanUp(DataSource mysqlDS) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		conn.createStatement().executeUpdate("TRUNCATE TABLE "+"article");
		conn.close();
	}

	/**
	 * @param oracleDS
	 * @param mysqlDS
	 * @param fields
	 * @return
	 * @throws BeanIntrospectionException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Job buildJob(DataSource oracleDS, DataSource mysqlDS, String[] fields, String tableName,final Class recordClass)
			throws BeanIntrospectionException {
		Job job = aNewJob()
				.named(tableName+"Job")
                .reader(new JdbcRecordReader(oracleDS, QueryBuilder.selectQueryBuilder(tableName, fields)))
                .mapper(new CustomRecordMapper<>(recordClass))
                .writer(new JdbcRecordWriter(mysqlDS, QueryBuilder.insertQueryBuilder(tableName, fields), new BeanPropertiesPreparedStatementProvider(recordClass, fields)))
                .enableJmx(true)
                .pipelineListener(new CustomJobListener())
                .jobListener(new CustomJobListener())
                .build();
		return job;
	}

}
